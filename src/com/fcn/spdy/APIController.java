package com.fcn.spdy;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class APIController {

	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<Void> test() {
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "apis/GetStatistics")
	public @ResponseBody Statistics GetStatistics(
			@PathVariable String email) {
		Statistics statistics = new Statistics();
		statistics.throughput = 10.0;
		statistics.avgRTT = 2.10;
		return statistics;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "Offers/GetNoOfInstalls")
	public ResponseEntity<Statistics> getNumberOfInstalls(
			@RequestParam(required = true) String id) {
		Statistics statistics = new Statistics();
		statistics.throughput = 10.0;
		statistics.avgRTT = 2.10;
		ResponseEntity<Statistics> response = new ResponseEntity<Statistics>(
				statistics, HttpStatus.OK);
		return response;
	}

}
