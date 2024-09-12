package org.jobrunr.devoxx.demo3;

import org.jobrunr.devoxx.common.BeerService;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("demo3")
public class Demo3API {

    private final JobScheduler jobScheduler;
    private final BeerService beerService;

    public Demo3API(JobScheduler jobScheduler, BeerService beerService) {
        this.jobScheduler = jobScheduler;
        this.beerService = beerService;
    }

    @GetMapping("/brew-beer")
    public String brewSomeBeer(@RequestParam(required = false, defaultValue = "Duvel") String beerType, @RequestParam(required = false, defaultValue = "toHighTemperature") String somethingHasGoneWrong) {
        // try to limit the amount of retries if our beer brewing process goes wrong
        jobScheduler.enqueue(() -> beerService.brewBeer(beerType, somethingHasGoneWrong));
        return "ok";
    }
}
