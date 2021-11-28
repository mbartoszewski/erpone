package com.bartoszewski.erpone.Controller;

import com.bartoszewski.erpone.Entity.ThingGroup;
import com.bartoszewski.erpone.Service.BaseService;
import com.bartoszewski.erpone.Service.ThingGroupService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/things/group")
public class ThingGroupController extends BaseController<ThingGroup, Long> {

	ThingGroupService thingGroupService;

	public ThingGroupController(BaseService<ThingGroup, Long> service, ThingGroupService thingGroupService) {
		super(service);
		this.thingGroupService = thingGroupService;
	}

}
