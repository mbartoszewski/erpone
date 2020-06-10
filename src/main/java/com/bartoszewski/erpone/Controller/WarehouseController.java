package com.bartoszewski.erpone.Controller;

import com.bartoszewski.erpone.Entity.Warehouse;
import com.bartoszewski.erpone.Service.BaseService;
import com.bartoszewski.erpone.Service.WarehouseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/warehouses")
public class WarehouseController extends BaseController<Warehouse, Long>
{
    WarehouseService WarehousesService;

    @Autowired
    public WarehouseController(BaseService<Warehouse, Long> service, WarehouseService warehousesService)
    {
        super(service);
        this.WarehousesService = warehousesService;
    }

}