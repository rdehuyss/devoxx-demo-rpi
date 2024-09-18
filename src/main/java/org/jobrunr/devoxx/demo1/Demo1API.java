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

    @GetMapping("/drink-beer-via-lambda")
    public String createJobViaLambda(@RequestParam(required = false, defaultValue = "Duvel") String beerType) {
        jobScheduler.enqueue(() -> beerService.drinkBeer(Beer.fromLabel(beerType)));
        return "ok";
    }

    @GetMapping("/drink-beer-via-jobrequest")
    public String createJobViaJobRequest(@RequestParam(required = false, defaultValue = "Duvel") String beerType) {
        jobRequestScheduler.enqueue(new DrinkBeerRequest(Beer.fromLabel(beerType)));
        return "ok";
    }

    @GetMapping("/brew-beer")
    public String brewBeer(@RequestParam(required = false, defaultValue = "Duvel") String beerType) {
        jobScheduler.enqueue(() -> beerService.brewBeer(Beer.fromLabel(beerType)));
        return "ok";
    }
}

