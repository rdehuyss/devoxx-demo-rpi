package org.jobrunr.devoxx.demo1;

import org.jobrunr.devoxx.common.Beer;
import org.jobrunr.devoxx.common.BeerService;
import org.jobrunr.devoxx.common.DrinkBeerRequestHandler.DrinkBeerRequest;
import org.jobrunr.scheduling.JobRequestScheduler;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("demo1")
public class Demo1API {

    private final JobScheduler jobScheduler;
    private final JobRequestScheduler jobRequestScheduler;
    private final BeerService beerService;

    public Demo1API(JobScheduler jobScheduler, JobRequestScheduler jobRequestScheduler, BeerService beerService) {
        this.jobScheduler = jobScheduler;
        this.jobRequestScheduler = jobRequestScheduler;
        this.beerService = beerService;
    }

    @GetMapping(value = "/beer", produces = APPLICATION_JSON_VALUE)
    public String getBeers() {
        return "{ \"beer\": [" + Stream.of(Beer.values()).map(Beer::asJson).collect(Collectors.joining(",\n")) + "]}";
    }

    @GetMapping("/drink-beer-via-lambda")
    public String createJobViaLambda(@RequestParam(required = false, defaultValue = "Duvel") String beerType) {
        var beer = Beer.fromLabel(beerType);
        jobScheduler.enqueue(() -> beerService.drinkBeer(beer));
        return "ok, drinking beer via lambda for " + beerType;
    }

    @GetMapping("/drink-beer-via-jobrequest")
    public String createJobViaJobRequest(@RequestParam(required = false, defaultValue = "Duvel") String beerType) {
        var beer = Beer.fromLabel(beerType);
        jobRequestScheduler.enqueue(new DrinkBeerRequest(beer));
        return "ok, drinking beer via job request for " + beerType;
    }

    @GetMapping("/brew-beer")
    public String brewBeer(@RequestParam(required = false, defaultValue = "Duvel") String beerType) {
        var beer = Beer.fromLabel(beerType);
        jobScheduler.enqueue(() -> beerService.brewBeer(beer));
        return "ok, brewing " + beerType;
    }
}

