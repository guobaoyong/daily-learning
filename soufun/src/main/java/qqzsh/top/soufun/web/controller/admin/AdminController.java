package qqzsh.top.soufun.web.controller.admin;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import qqzsh.top.soufun.base.*;
import qqzsh.top.soufun.entity.HouseSubscribe;
import qqzsh.top.soufun.entity.SupportAddress;
import qqzsh.top.soufun.entity.User;
import qqzsh.top.soufun.repository.HouseSubscribeRespository;
import qqzsh.top.soufun.repository.RoleRepository;
import qqzsh.top.soufun.service.IUserService;
import qqzsh.top.soufun.service.ServiceMultiResult;
import qqzsh.top.soufun.service.ServiceResult;
import qqzsh.top.soufun.service.house.IAddressService;
import qqzsh.top.soufun.service.house.IHouseService;
import qqzsh.top.soufun.service.house.IQiNiuService;
import qqzsh.top.soufun.web.dto.*;
import qqzsh.top.soufun.web.form.*;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-08-30 10:04
 * @description
 */
@Controller
public class AdminController {

    @Autowired
    private IQiNiuService qiNiuService;

    @Autowired
    private IAddressService addressService;

    @Autowired
    private IHouseService houseService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Gson gson;

    /**
     * 后台管理中心
     * @return
     */
    @GetMapping("/admin/center")
    public String adminCenterPage(Model model) {
        //获取当前用户的登录id，用于显示个人信息
        model.addAttribute("adminId",LoginUserUtil.getLoginUserId());
        return "admin/center";
    }

    /**
     * 欢迎页
     * @return
     */
    @GetMapping("/admin/welcome")
    public String welcomePage() {
        return "admin/welcome";
    }

    /**
     * 管理员登录页
     * @return
     */
    @GetMapping("/admin/login")
    public String adminLoginPage() {
        return "admin/login";
    }

    /**
     * 房源列表页
     * @return
     */
    @GetMapping("admin/house/list")
    public String houseListPage() {
        return "admin/house-list";
    }

    /**
     * 用户列表页
     * @return
     */
    @GetMapping("admin/user/list")
    public String UserListPage() {
        return "admin/user-list";
    }

    /**
     * 城市/区域列表页
     */
    @GetMapping("admin/city/list")
    public String CityListPage() {
        return "admin/city-list";
    }

    /**
     * 城市/区域列表页
     */
    @GetMapping("admin/subway/list")
    public String SubwayListPage() {
        return "admin/subway-list";
    }

    @PostMapping("admin/houses")
    @ResponseBody
    public ApiDataTableResponse houses(@ModelAttribute DatatableSearch searchBody) {
        ServiceMultiResult<HouseDTO> result = houseService.adminQuery(searchBody);

        ApiDataTableResponse response = new ApiDataTableResponse(ApiResponse.Status.SUCCESS);
        response.setData(result.getResult());
        response.setRecordsFiltered(result.getTotal());
        response.setRecordsTotal(result.getTotal());

        response.setDraw(searchBody.getDraw());
        return response;
    }

    @PostMapping("admin/users")
    @ResponseBody
    public ApiDataTableResponse users(@ModelAttribute DatatableSearch searchBody){
        ApiDataTableResponse response = new ApiDataTableResponse(ApiResponse.Status.SUCCESS);
        List<User> all = userService.findAll();
        List<UserDTO> alls = new ArrayList<>();
        all.forEach(user -> {
            UserDTO userDTO = new UserDTO();
            modelMapper.map(user,userDTO);
            userDTO.setRole(userService.findByUserId(user.getId()).getName());
            userDTO.setStatus(user.getStatus());
            alls.add(userDTO);
        });
        response.setData(alls);
        response.setRecordsFiltered(all.size());
        response.setRecordsTotal(all.size());

        response.setDraw(searchBody.getDraw());
        return response;
    }

    @PostMapping("admin/citys")
    @ResponseBody
    public ApiDataTableResponse citys(@ModelAttribute DatatableSearch searchBody){
        ApiDataTableResponse response = new ApiDataTableResponse(ApiResponse.Status.SUCCESS);
        List<CityForm> all = addressService.findAll();
        response.setData(all);
        response.setRecordsFiltered(all.size());
        response.setRecordsTotal(all.size());

        response.setDraw(searchBody.getDraw());
        return response;
    }

    @PostMapping("admin/subways")
    @ResponseBody
    public ApiDataTableResponse subways(@ModelAttribute DatatableSearch searchBody){
        ApiDataTableResponse response = new ApiDataTableResponse(ApiResponse.Status.SUCCESS);
        List<SubwayForm> all = addressService.findAllSubway();
        response.setData(all);
        response.setRecordsFiltered(all.size());
        response.setRecordsTotal(all.size());

        response.setDraw(searchBody.getDraw());
        return response;
    }


    /**
     * 新增房源功能页
     * @return
     */
    @GetMapping("admin/add/house")
    public String addHousePage() {
        return "admin/house-add";
    }

    /**
     * 新增用户功能页
     */
    @GetMapping("admin/add/user")
    public String addUserPage() {
        return "admin/user-add";
    }

    /**
     * 新增城市/区域功能页
     */
    @GetMapping("admin/add/city_region")
    public String addCityAndRegionPage() {
        return "admin/city-add";
    }

    /**
     * 新增地铁/站点功能页
     */
    @GetMapping("admin/add/subway")
    public String addSubwayPage() {
        return "admin/subway-add";
    }

    /**
     * 上传图片接口
     * @param file
     * @return
     */
    @PostMapping(value = "admin/upload/photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ApiResponse uploadPhoto(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ApiResponse.ofStatus(ApiResponse.Status.NOT_VALID_PARAM);
        }

        String fileName = file.getOriginalFilename();

        try {
            InputStream inputStream = file.getInputStream();
            Response response = qiNiuService.uploadFile(inputStream);
            if (response.isOK()) {
                QiNiuPutRet ret = gson.fromJson(response.bodyString(), QiNiuPutRet.class);
                return ApiResponse.ofSuccess(ret);
            } else {
                return ApiResponse.ofMessage(response.statusCode, response.getInfo());
            }

        } catch (QiniuException e) {
            Response response = e.response;
            try {
                return ApiResponse.ofMessage(response.statusCode, response.bodyString());
            } catch (QiniuException e1) {
                e1.printStackTrace();
                return ApiResponse.ofStatus(ApiResponse.Status.INTERNAL_SERVER_ERROR);
            }
        } catch (IOException e) {
            return ApiResponse.ofStatus(ApiResponse.Status.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 新增房源接口
     * @param houseForm
     * @param bindingResult
     * @return
     */
    @PostMapping("admin/add/house")
    @ResponseBody
    public ApiResponse addHouse(@Valid @ModelAttribute("form-house-add") HouseForm houseForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ApiResponse(HttpStatus.BAD_REQUEST.value(), bindingResult.getAllErrors().get(0).getDefaultMessage(), null);
        }

        if (houseForm.getPhotos() == null || houseForm.getCover() == null) {
            return ApiResponse.ofMessage(HttpStatus.BAD_REQUEST.value(), "必须上传图片");
        }

        Map<SupportAddress.Level, SupportAddressDTO> addressMap = addressService.findCityAndRegion(houseForm.getCityEnName(), houseForm.getRegionEnName());
        if (addressMap.keySet().size() != 2) {
            return ApiResponse.ofStatus(ApiResponse.Status.NOT_VALID_PARAM);
        }

        ServiceResult<HouseDTO> result = houseService.save(houseForm);
        if (result.isSuccess()) {
            return ApiResponse.ofSuccess(result.getResult());
        }

        return ApiResponse.ofSuccess(ApiResponse.Status.NOT_VALID_PARAM);
    }

    /**
     * 新增用户接口
     * @param userForm
     * @param bindingResult
     * @return
     */
    @PostMapping("admin/add/user")
    @ResponseBody
    public ApiResponse addUser(@Valid @ModelAttribute("form-user-add") UserForm userForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ApiResponse(HttpStatus.BAD_REQUEST.value(), bindingResult.getAllErrors().get(0).getDefaultMessage(), null);
        }
        //判断用户名是否重复
        if (checkName(userForm.getName()) == null){
            //是否存在
            if (checkNum(userForm.getPhoneNumber()) == null){
                if (!LoginUserUtil.checkTelephone(userForm.getPhoneNumber())){
                    return new ApiResponse(HttpStatus.BAD_REQUEST.value(),"手机号不符合规范",null);
                }
            }else {
                return new ApiResponse(HttpStatus.BAD_REQUEST.value(),"手机号已存在",null);
            }
            if (!userForm.getEmail().isEmpty()){
                if (!LoginUserUtil.checkEmail(userForm.getEmail())){
                    return new ApiResponse(HttpStatus.BAD_REQUEST.value(),"邮箱不符合规范",null);
                }
            }
        }else {
            return new ApiResponse(HttpStatus.BAD_REQUEST.value(),"用户名已存在",null);
        }
        userService.save(userForm);
        return ApiResponse.ofSuccess(ApiResponse.Status.NOT_VALID_PARAM);
    }

    /**
     * 城市/区域添加
     * @param cityForm
     * @param bindingResult
     * @return
     */
    @PostMapping("admin/add/city_region")
    @ResponseBody
    public ApiResponse addCity(@Valid @ModelAttribute("form-user-add") CityForm cityForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new ApiResponse(HttpStatus.BAD_REQUEST.value(), bindingResult.getAllErrors().get(0).getDefaultMessage(), null);
        }
        List<String> region = new ArrayList<>();
        String[] split = cityForm.getRegion().split(";");
        for (String s : split) {
            region.add(s);
        }
        addressService.save(cityForm.getCity(),region);
        return ApiResponse.ofSuccess(ApiResponse.Status.NOT_VALID_PARAM);
    }

    /**
     * 地铁/线路添加
     * @param subwayForm
     * @param bindingResult
     * @return
     */
    @PostMapping("admin/add/subway")
    @ResponseBody
    public ApiResponse addSubway(@Valid @ModelAttribute("form-subway-add") SubwayForm subwayForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new ApiResponse(HttpStatus.BAD_REQUEST.value(), bindingResult.getAllErrors().get(0).getDefaultMessage(), null);
        }
        System.out.println(subwayForm);
        List<String> region = new ArrayList<>();
        String[] split = subwayForm.getStation().split(";");
        for (String s : split) {
            region.add(s);
        }
        addressService.saveSubway(subwayForm,region);
        return ApiResponse.ofSuccess(ApiResponse.Status.NOT_VALID_PARAM);
    }

    @DeleteMapping("admin/city/delete/{id}")
    @ResponseBody
    public ApiResponse deleteCity(@PathVariable(value = "id") Long id){
        if (id <= 0) {
            return ApiResponse.ofStatus(ApiResponse.Status.NOT_VALID_PARAM);
        }
        addressService.delete(id);
        return ApiResponse.ofSuccess(null);
    }

    @DeleteMapping("admin/subway/delete/{id}")
    @ResponseBody
    public ApiResponse deleteSubway(@PathVariable(value = "id") Long id){
        if (id <= 0) {
            return ApiResponse.ofStatus(ApiResponse.Status.NOT_VALID_PARAM);
        }
        addressService.deleteSubway(id);
        return ApiResponse.ofSuccess(null);
    }

    /**
     * 判断用户名是否存在
     * @param name
     * @return
     */
    private User checkName(String name){
        return userService.findUserByName(name);
    }

    /**
     * 判断手机号是否存在
     * @param num
     * @return
     */
    private User checkNum(String num){
        return userService.findUserByTelephone(num);
    }

    /**
     * 房源信息编辑页
     * @return
     */
    @GetMapping("admin/house/edit")
    public String houseEditPage(@RequestParam(value = "id") Long id, Model model) {

        if (id == null || id < 1) {
            return "404";
        }

        ServiceResult<HouseDTO> serviceResult = houseService.findCompleteOne(id);
        if (!serviceResult.isSuccess()) {
            return "404";
        }

        HouseDTO result = serviceResult.getResult();
        model.addAttribute("house", result);

        Map<SupportAddress.Level, SupportAddressDTO> addressMap = addressService.findCityAndRegion(result.getCityEnName(), result.getRegionEnName());
        model.addAttribute("city", addressMap.get(SupportAddress.Level.CITY));
        model.addAttribute("region", addressMap.get(SupportAddress.Level.REGION));

        HouseDetailDTO detailDTO = result.getHouseDetail();
        ServiceResult<SubwayDTO> subwayServiceResult = addressService.findSubway(detailDTO.getSubwayLineId());
        if (subwayServiceResult.isSuccess()) {
            model.addAttribute("subway", subwayServiceResult.getResult());
        }

        ServiceResult<SubwayStationDTO> subwayStationServiceResult = addressService.findSubwayStation(detailDTO.getSubwayStationId());
        if (subwayStationServiceResult.isSuccess()) {
            model.addAttribute("station", subwayStationServiceResult.getResult());
        }

        return "admin/house-edit";
    }

    /**
     * 用户信息编辑页
     * @return
     */
    @GetMapping("admin/user/edit")
    public String UserEditPage(@RequestParam(value = "id") Long id, Model model) {

        if (id == null || id < 1) {
            return "404";
        }

        ServiceResult<UserDTO> serviceResult = userService.findById(id);
        if (!serviceResult.isSuccess()) {
            return "404";
        }

        UserDTO result = serviceResult.getResult();
        result.setRole(userService.findByUserId(id).getName());
        model.addAttribute("user", result);

        return "admin/user-edit";
    }

    /**
     * 编辑接口
     */
    @PostMapping("admin/house/edit")
    @ResponseBody
    public ApiResponse saveHouse(@Valid @ModelAttribute("form-house-edit") HouseForm houseForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ApiResponse(HttpStatus.BAD_REQUEST.value(), bindingResult.getAllErrors().get(0).getDefaultMessage(), null);
        }

        Map<SupportAddress.Level, SupportAddressDTO> addressMap = addressService.findCityAndRegion(houseForm.getCityEnName(), houseForm.getRegionEnName());

        if (addressMap.keySet().size() != 2) {
            return ApiResponse.ofSuccess(ApiResponse.Status.NOT_VALID_PARAM);
        }

        ServiceResult result = houseService.update(houseForm);
        if (result.isSuccess()) {
            return ApiResponse.ofSuccess(null);
        }

        ApiResponse response = ApiResponse.ofStatus(ApiResponse.Status.BAD_REQUEST);
        response.setMessage(result.getMessage());
        return response;
    }

    /**
     * 用户信息编辑保存接口
     */
    @PostMapping("admin/user/edit")
    @ResponseBody
    public ApiResponse saveUser(@Valid @ModelAttribute("form-user-edit") UserForm userForm, BindingResult bindingResult) {
        System.out.println(userForm);

        if (bindingResult.hasErrors()) {
            return new ApiResponse(HttpStatus.BAD_REQUEST.value(), bindingResult.getAllErrors().get(0).getDefaultMessage(), null);
        }

        //判断用户名是否重复
        if (checkName(userForm.getName()) == null || checkName(userForm.getName()).getId() == userForm.getId()){
            //是否存在
            if (checkNum(userForm.getPhoneNumber()) == null || checkNum(userForm.getPhoneNumber()).getId() == userForm.getId()){
                if (!LoginUserUtil.checkTelephone(userForm.getPhoneNumber())){
                    return new ApiResponse(HttpStatus.BAD_REQUEST.value(),"手机号不符合规范",null);
                }
            }else {
                return new ApiResponse(HttpStatus.BAD_REQUEST.value(),"手机号已存在",null);
            }
            if (!userForm.getEmail().isEmpty()){
                if (!LoginUserUtil.checkEmail(userForm.getEmail())){
                    return new ApiResponse(HttpStatus.BAD_REQUEST.value(),"邮箱不符合规范",null);
                }
            }
        }else {
            return new ApiResponse(HttpStatus.BAD_REQUEST.value(),"用户名已存在",null);
        }
        userService.update(userForm);
        return ApiResponse.ofSuccess(ApiResponse.Status.NOT_VALID_PARAM);
    }

    /**
     * 移除图片接口
     * @param id
     * @return
     */
    @DeleteMapping("admin/house/photo")
    @ResponseBody
    public ApiResponse removeHousePhoto(@RequestParam(value = "id") Long id) {
        ServiceResult result = this.houseService.removePhoto(id);

        if (result.isSuccess()) {
            return ApiResponse.ofStatus(ApiResponse.Status.SUCCESS);
        } else {
            return ApiResponse.ofMessage(HttpStatus.BAD_REQUEST.value(), result.getMessage());
        }
    }

    /**
     * 修改封面接口
     * @param coverId
     * @param targetId
     * @return
     */
    @PostMapping("admin/house/cover")
    @ResponseBody
    public ApiResponse updateCover(@RequestParam(value = "cover_id") Long coverId,
                                   @RequestParam(value = "target_id") Long targetId) {
        ServiceResult result = this.houseService.updateCover(coverId, targetId);

        if (result.isSuccess()) {
            return ApiResponse.ofStatus(ApiResponse.Status.SUCCESS);
        } else {
            return ApiResponse.ofMessage(HttpStatus.BAD_REQUEST.value(), result.getMessage());
        }
    }

    /**
     * 增加标签接口
     * @param houseId
     * @param tag
     * @return
     */
    @PostMapping("admin/house/tag")
    @ResponseBody
    public ApiResponse addHouseTag(@RequestParam(value = "house_id") Long houseId,
                                   @RequestParam(value = "tag") String tag) {
        if (houseId < 1 || Strings.isNullOrEmpty(tag)) {
            return ApiResponse.ofStatus(ApiResponse.Status.BAD_REQUEST);
        }

        ServiceResult result = this.houseService.addTag(houseId, tag);
        if (result.isSuccess()) {
            return ApiResponse.ofStatus(ApiResponse.Status.SUCCESS);
        } else {
            return ApiResponse.ofMessage(HttpStatus.BAD_REQUEST.value(), result.getMessage());
        }
    }

    /**
     * 移除标签接口
     * @param houseId
     * @param tag
     * @return
     */
    @DeleteMapping("admin/house/tag")
    @ResponseBody
    public ApiResponse removeHouseTag(@RequestParam(value = "house_id") Long houseId,
                                      @RequestParam(value = "tag") String tag) {
        if (houseId < 1 || Strings.isNullOrEmpty(tag)) {
            return ApiResponse.ofStatus(ApiResponse.Status.BAD_REQUEST);
        }

        ServiceResult result = this.houseService.removeTag(houseId, tag);
        if (result.isSuccess()) {
            return ApiResponse.ofStatus(ApiResponse.Status.SUCCESS);
        } else {
            return ApiResponse.ofMessage(HttpStatus.BAD_REQUEST.value(), result.getMessage());
        }
    }

    /**
     * 审核接口
     * @param id
     * @param operation
     * @return
     */
    @PutMapping("admin/house/operate/{id}/{operation}")
    @ResponseBody
    public ApiResponse operateHouse(@PathVariable(value = "id") Long id,
                                    @PathVariable(value = "operation") int operation) {
        if (id <= 0) {
            return ApiResponse.ofStatus(ApiResponse.Status.NOT_VALID_PARAM);
        }
        ServiceResult result;

        switch (operation) {
            case HouseOperation.PASS:
                result = this.houseService.updateStatus(id, HouseStatus.PASSES.getValue());
                break;
            case HouseOperation.PULL_OUT:
                result = this.houseService.updateStatus(id, HouseStatus.NOT_AUDITED.getValue());
                break;
            case HouseOperation.DELETE:
                result = this.houseService.updateStatus(id, HouseStatus.DELETED.getValue());
                break;
            case HouseOperation.RENT:
                result = this.houseService.updateStatus(id, HouseStatus.RENTED.getValue());
                break;
            default:
                return ApiResponse.ofStatus(ApiResponse.Status.BAD_REQUEST);
        }

        if (result.isSuccess()) {
            return ApiResponse.ofSuccess(null);
        }
        return ApiResponse.ofMessage(HttpStatus.BAD_REQUEST.value(),
                result.getMessage());
    }

    /**
     * 封禁、解封、删除
     * @param id
     * @param operation
     * @return
     */
    @PutMapping("admin/user/operate/{id}/{operation}")
    @ResponseBody
    public ApiResponse operateUser(@PathVariable(value = "id") Long id,
                                    @PathVariable(value = "operation") int operation) {
        if (id <= 0) {
            return ApiResponse.ofStatus(ApiResponse.Status.NOT_VALID_PARAM);
        }
        ServiceResult result;

        switch (operation) {
            case UserOperation.KILL:
                result = this.userService.updateStatus(id,1);
                break;
            case UserOperation.UNKILL:
                result = this.userService.updateStatus(id,0);
                break;
            case UserOperation.DELETE:
                result = this.userService.deleteUser(id);
                break;
            default:
                return ApiResponse.ofStatus(ApiResponse.Status.BAD_REQUEST);
        }

        if (result.isSuccess()) {
            return ApiResponse.ofSuccess(null);
        }
        return ApiResponse.ofMessage(HttpStatus.BAD_REQUEST.value(),
                result.getMessage());
    }

    @GetMapping("admin/house/subscribe")
    public String houseSubscribe() {
        return "admin/subscribe";
    }

    @GetMapping("admin/house/subscribe/list")
    @ResponseBody
    public ApiResponse subscribeList(@RequestParam(value = "draw") int draw,
                                     @RequestParam(value = "start") int start,
                                     @RequestParam(value = "length") int size) {
        ServiceMultiResult<Pair<HouseDTO, HouseSubscribeDTO>> result = houseService.findSubscribeList(start, size);

        ApiDataTableResponse response = new ApiDataTableResponse(ApiResponse.Status.SUCCESS);
        response.setData(result.getResult());
        response.setDraw(draw);
        response.setRecordsFiltered(result.getTotal());
        response.setRecordsTotal(result.getTotal());
        return response;
    }

    @GetMapping("admin/user/{userId}")
    @ResponseBody
    public ApiResponse getUserInfo(@PathVariable(value = "userId") String userId) {
        long id = 0L;
        //预约看房显示此条预约看房的联系方式
        if (userId.startsWith("y")){
            id = Integer.parseInt(userId.substring(1));
            if (id == 0 || id < 1) {
                return ApiResponse.ofStatus(ApiResponse.Status.BAD_REQUEST);
            }
            //从预约表里查询信息
            HouseSubscribe byId = houseService.findById(id);
            ServiceResult<UserDTO> serviceResult = userService.findById(byId.getUserId());
            serviceResult.getResult().setPhoneNumber(byId.getTelephone());
            if (!serviceResult.isSuccess()) {
                return ApiResponse.ofStatus(ApiResponse.Status.NOT_FOUND);
            } else {
                return ApiResponse.ofSuccess(serviceResult.getResult());
            }
        }else {
            id = Integer.parseInt(userId);
            if (id == 0 || id < 1) {
                return ApiResponse.ofStatus(ApiResponse.Status.BAD_REQUEST);
            }
            ServiceResult<UserDTO> serviceResult = userService.findById(id);
            if (!serviceResult.isSuccess()) {
                return ApiResponse.ofStatus(ApiResponse.Status.NOT_FOUND);
            } else {
                return ApiResponse.ofSuccess(serviceResult.getResult());
            }
        }
    }

    @PostMapping("admin/finish/subscribe")
    @ResponseBody
    public ApiResponse finishSubscribe(@RequestParam(value = "house_id") Long houseId) {
        if (houseId < 1) {
            return ApiResponse.ofStatus(ApiResponse.Status.BAD_REQUEST);
        }

        ServiceResult serviceResult = houseService.finishSubscribe(houseId);
        if (serviceResult.isSuccess()) {
            return ApiResponse.ofSuccess("");
        } else {
            return ApiResponse.ofMessage(ApiResponse.Status.BAD_REQUEST.getCode(), serviceResult.getMessage());
        }
    }

}
