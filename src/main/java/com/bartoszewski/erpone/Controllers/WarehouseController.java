package com.bartoszewski.erpone.Controllers;

import com.bartoszewski.erpone.Models.Warehouse;
import com.bartoszewski.erpone.Services.BaseService;
import com.bartoszewski.erpone.Services.WarehouseService;

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
        // TODO Auto-generated constructor stub
        this.WarehousesService = warehousesService;
    }

}