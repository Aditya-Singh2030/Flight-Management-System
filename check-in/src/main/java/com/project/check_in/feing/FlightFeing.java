package com.project.check_in.feing;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="FLIGHTS")
public interface FlightFeing {
	
	

}
