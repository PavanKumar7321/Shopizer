package com.salesmanager.shop.store.api.v1.product;

import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.salesmanager.core.business.services.catalog.product.manufacturer.ManufacturerService;
import com.salesmanager.core.model.catalog.product.manufacturer.Manufacturer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.manufacturer.PersistableManufacturer;
import com.salesmanager.shop.model.catalog.manufacturer.ReadableManufacturer;
import com.salesmanager.shop.model.catalog.manufacturer.ReadableManufacturerList;
import com.salesmanager.shop.model.entity.EntityExists;
import com.salesmanager.shop.model.entity.ListCriteria;
import com.salesmanager.shop.store.controller.manufacturer.facade.ManufacturerFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Manufacturer management Collection, Manufacturer ...
 *
 * @author c.samson
 */
@Controller
@RequestMapping("/api/v1")
@Api(tags = { "Manufacturer / Brand management resource (Manufacturer / Brand Management Api)" })
@SwaggerDefinition(tags = {
		@Tag(name = "Manufacturer / Brand Management Api", description = "Edit Manufacturer / Brand") })
 public class WatchesManufactureApi{
	

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductManufacturerApi.class);

	@Inject
	private ManufacturerService manufacturerService;

	@Inject
	private ManufacturerFacade manufacturerFacade;
	
	
	@RequestMapping(value = "/private/Decent", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public PersistableManufacturer create(@Valid @RequestBody PersistableManufacturer manufacturer,
			@ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language, HttpServletResponse response) {

		try {
			manufacturerFacade.saveOrUpdateManufacturer(manufacturer, merchantStore, language);

			return manufacturer;

		} catch (Exception e) {
			LOGGER.error("Error while creating manufacturer", e);
			try {
				response.sendError(503, "Error while creating manufacturer " + e.getMessage());
			} catch (Exception ignore) {
			}

			return null;
		}
	}
	
}