package org.jobrunr.javazone.demo3;

import org.jobrunr.javazone.common.VikingService;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("demo3")
public class Demo3API {

    private final JobScheduler jobScheduler;
    private final VikingService vikingService;

    public Demo3API(JobScheduler jobScheduler, VikingService vikingService) {
        this.jobScheduler = jobScheduler;
        this.vikingService = vikingService;
    }

    @GetMapping("/farm-job")
    public String farmSomeFood(@RequestParam(required = false, defaultValue = "tomatoes") String food, @RequestParam(required = false, defaultValue = "") String naturalDisaster) {
        // try to limit the amount of retries
        jobScheduler.enqueue(() -> vikingService.doFarming(food, naturalDisaster));
        return "ok";
    }
}
