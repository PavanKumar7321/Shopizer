package com.salesmanager.shop.store.api.v1.category;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.model.catalog.category.PersistableCategory;
import com.salesmanager.shop.model.catalog.category.ReadableCategory;
import com.salesmanager.shop.model.catalog.category.ReadableCategoryList;
import com.salesmanager.shop.model.entity.EntityExists;
import com.salesmanager.shop.model.entity.ListCriteria;
import com.salesmanager.shop.store.api.exception.UnauthorizedException;
import com.salesmanager.shop.store.controller.category.facade.CategoryFacade;
import com.salesmanager.shop.store.controller.user.facade.UserFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping(value = "/api/v1")
@Api(tags = { "Category management resource (Category Management Api)" })
@SwaggerDefinition(tags = {
@Tag(name = "Category management resource", description = "Manage category and attached products") })

public class WatchesCategoryApi {

private static final int DEFAULT_CATEGORY_DEPTH = 0;

@Inject
private CategoryFacade categoryFacade;

@Inject
private UserFacade userFacade;


@ResponseStatus(HttpStatus.CREATED)
@PostMapping(value = "/private/category_Decent", produces = { APPLICATION_JSON_VALUE })
@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "string", defaultValue = "DEFAULT"),
@ApiImplicitParam(name = "lang", dataType = "string", defaultValue = "en") })
public PersistableCategory create(
@Valid @RequestBody PersistableCategory category_Ooha,
@ApiIgnore MerchantStore merchantStore,
@ApiIgnore Language language) {

// superadmin, admin and admin_catalogue
 String authenticatedUser = userFacade.authenticatedUser();
if (authenticatedUser == null) {
throw new UnauthorizedException();
 }
userFacade.authorizedGroup(authenticatedUser, Stream.of(Constants.GROUP_SUPERADMIN, Constants.GROUP_ADMIN, Constants.GROUP_ADMIN_CATALOGUE, Constants.GROUP_ADMIN_RETAIL).collect(Collectors.toList()));

return categoryFacade.saveCategory(merchantStore, category_Ooha);
}

@GetMapping(value = "/private/category_Decent", produces = { APPLICATION_JSON_VALUE })
@ApiOperation(httpMethod = "GET", value = "Get category hierarchy from root. Supports filtering FEATURED_CATEGORIES and VISIBLE ONLY by adding ?filter=[featured] or ?filter=[visible] or ? filter=[featured,visible", notes = "Does not return any product attached")
@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "string", defaultValue = "DEFAULT"),
		@ApiImplicitParam(name = "lang", dataType = "string", defaultValue = "en") })
public ReadableCategoryList list(
		@RequestParam(value = "filter", required = false) List<String> filter,
		@RequestParam(value = "name", required = false) String name,
		@ApiIgnore MerchantStore merchantStore,
		@ApiIgnore Language language,
		@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
		@RequestParam(value = "count", required = false, defaultValue = "10") Integer count) {


	ListCriteria criteria = new ListCriteria();
	criteria.setName(name);
	return categoryFacade.getCategoryHierarchy(merchantStore, criteria, DEFAULT_CATEGORY_DEPTH, language, filter,
			page, count);
}

@DeleteMapping(value = "/private/category_Decent/{id}", produces = { APPLICATION_JSON_VALUE })
@ResponseStatus(OK)
public void delete(@PathVariable("id") Long categoryId, @ApiIgnore MerchantStore merchantStore) {

	// superadmin, admin and admin_catalogue
	String authenticatedUser = userFacade.authenticatedUser();
	if (authenticatedUser == null) {
		throw new UnauthorizedException();
	}

	userFacade.authorizedGroup(authenticatedUser, Stream.of(Constants.GROUP_SUPERADMIN, Constants.GROUP_ADMIN, Constants.GROUP_ADMIN_CATALOGUE, Constants.GROUP_ADMIN_RETAIL).collect(Collectors.toList()));

	categoryFacade.deleteCategory(categoryId, merchantStore);
}

@PutMapping(value = "/private/category_Decent/{id}", produces = { APPLICATION_JSON_VALUE })
@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "string", defaultValue = "DEFAULT") })
public PersistableCategory update(@PathVariable Long id, @Valid @RequestBody PersistableCategory category,
		@ApiIgnore MerchantStore merchantStore) {

	// superadmin, admin and admin_catalogue
	String authenticatedUser = userFacade.authenticatedUser();
	if (authenticatedUser == null) {
		throw new UnauthorizedException();
	}

	userFacade.authorizedGroup(authenticatedUser, Stream.of(Constants.GROUP_SUPERADMIN, Constants.GROUP_ADMIN, Constants.GROUP_ADMIN_CATALOGUE, Constants.GROUP_ADMIN_RETAIL).collect(Collectors.toList()));


	category.setId(id);
	return categoryFacade.saveCategory(merchantStore, category);
}


}
