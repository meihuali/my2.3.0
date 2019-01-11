package com.example.a77299007.myapplication.base.baseBean;

import android.os.Environment;

/**
 * Created by wsl on 2017/4/6.
 * 统一常量管理
 */

public class Const {
    public static final String jsonHeader = "Content-Type: application/json\",\"Accept: application/json";
    public static final String AGR_PAGE = "agr_page";
    /*-------------------第三方--------------------------*/
    public static final String WX_APP_ID = "wxa0c4eecbb3933b46";
    public static final String QQ_APP_ID = "1106303418";
    public static final String secrekey = "ipaigongipaigong";
    public static final String iv = "0392039203920300";

    /*--------------SharePreference管理start---------------*/
    public static final String SP_NAME = "sp_prefs";
    public static final String USER_JSON = "user_info";
    public static final String USER_PHONE = "U_P";
    public static final String USER_PWD = "U_W";
    public static final String COOKIES = "cookies";
    public static final String JPUSH_ALIAS_SET_SUCCESS = "jpush_alias_success";
    public static final String IS_FIRST = "isFirst";
    public static final String NOW_TIME = "now_time";
    public static final String SELECT_ID = "selectId";
    public static final String ARR_IDS = "arrIds";
    public static final int DEFAULT_PROJECT_RADIUS = 250;
    public static final String VEHICLE_CODE = "vehicle_code";
    public static final String VEHICLE_ONLINE = "vehicle_online";
    public static final String SCREEN_WIDTH = "screen_width";
    public static final String SCREEN_HEIGHT = "screen_height";
    public static final String NAVIGATE_SETTING_JSON = "navigate_setting_json";
    public static final String NAVIGATE_SETTING_BEAN = "navigate_setting_bean";
    public static final String REMEMBER_PASSWORD = "remember_password";
    public static final String IS_TOKEN_FAILED = "IS_TOKEN_FAILED";
    public static final String MESSAGE_SUM = "MESSAGE_SUM";
    public static final String TIME_TO_BACKGROUND = "time_to_background";
    public static final String PROJECT_BEAN = "project_bean";
    public static final String ORDER_ID = "orderId";
    public static final String ORDER_VEHICLE = "order_vehicle";
    public static final String ORDER_CHECK_BEAN = "OrderCheckBean";
    public static final String EMPLOYEE_IDS = "employeeIds";
    public static final String MAIN_ID = "mainId";
    public static final String EMPLOYEE_NAMES = "employeeNames";
    public static final String MAIN_NAME = "mainName";
    public static final String PROJECT_NAME = "project_name";
    public static final String ASSIGN_NOW = "assignNow";
    public static final String TASK_DETAIL_POSITION = "position";
    public static final String CHECK_UPDATE = "check_update";
    public static final String HAS_ORDER_CHECK = "has_order_check";
    public static final String SALESMAN = "salesman";
    public static final String LOCATION_TIME = "location_time";
    public static final String TENANT_NAME = "tenant_name";
    public static final String TENANT_ID = "tenant_id";
    public static final String SEARCH_TEXT = "search_text";
    public static final String SELECT_ALL = "select_all";
    public static final String HAS_CHANGE = "hasChange";
    public static final String SHARE_APP_CONTENT = "派工调度好帮手";
    public static final String IS_APP_VISIBLE = "is_app_visible";
    public static final String ORDER_TIME = "order_time";
    public static final String PLAN_HOURS = "plan_hours";
    public static final String CLIENT_NAME = "client_name";
    public static final String IS_CLIENT_EDIT = "client_edit";     // 编辑客户
    public static final String CLIENT_ID = "client_id";            // 客戶管理的客戶id
    public static final String ROUTE_VEHICLE_ID = "RouteVehicleId";
    public static final String ROUTE_START_TIME = "RouteStartTime";
    public static final String ROUTE_END_TIME = "RouteEndTime";
    public static final String SHOW_LOCATION = "show_location";
    public static final String TASK_ID = "taskId";
    public static final String TASK_STATE = "taskState";
    public static final String SHOW_GUIDE = "showGuide";
    public static final String ORDER_TYPE_JSON = "orderTypeJson";

    /*--------------SharePreference管理end-----------------*/

    /*--------------本地文件管理-----------------*/
    public static String SD_DIR = Environment.getExternalStorageDirectory().getAbsolutePath();// SD card路径
    public static final String DIR_ROOT = SD_DIR + "/TujiRoot";
    public static final String DIR_APP = DIR_ROOT + "/IPaiGong";//派工易根目录
    public static final String DIR_PICTURE = DIR_APP + "/image";//图片目录
    public static final String DIR_DOWNLOAD = DIR_APP + "/download";//下载目录
    public static final String DIR_CACHE = DIR_APP + "/cache";//图片目录
    public static final String FILE_LOGO_NAME = DIR_PICTURE + "/logo1.jpg";//图片目录


    public final static String INTENT_CANCEL_DOWNLOAD = "intent_cancel_download";// 取消APk下载
    public final static String INTENT_START_DOWNLOAD = "intent_start_download";// 开始APk下载
    public static final int NOTIFICATION_ID = 0x001;
    public static boolean receiveDownloadStatus = true;

    /*--------------------常量--------------------------*/
    public static final String SET_CURR_TAB = "SET_CURR_TAB";
    public static final int HISTORY_tASK_RESULT_OK = 3;
    public static final String ASSIGN_TIME = "assignTime";
    public static final String TASK_DETAIL_STATE = "state";
    public static final int SEARCH_tASK_RESULT_OK = 4;
    public static final String HAVE_READ_POSITION = "HAVE_READ_POSITION";
    public static final String HAS_READ_SYSTEM_MESSAGE = "HAS_READ_SYSTEM_MESSAGE";
    public static final String HAS_READ_NOTIFICATION = "HAS_READ_NOTIFICATION";
    public static final String SETTLED_USER_PHONE = "SETTLED_USER_PHONE";
    public static final String SETTLED_IN = "SETTLED_IN";
    public static final String CONFIRM_ADDRESS = "CONFIRM_ADDRESS";
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
    public static final String DESTINATION = "destination";
    public static final String COMPANY_CITY = "COMPANY_CITY";
    public static final String SETTLED_USER_NAME = "SETTLED_USER_NAME";
    public static final String SETTLED_USER_PSW = "SETTLED_USER_PSW";
    public static final String SETTLED_NAME = "SETTLED_NAME";
    public static final String TODAY_TASK = "1";
    public static final String TOMORROW_TASK = "2";
    public static final String YESTERDAY_TASK = "3";
    public static final String IS_NAVIGATE = "is_navigate";
    public static final String CONTACT_PERSON_PHONE = "CONTACT_PERSON_PHONE";
    public static final String CONTACT_PERSON_NAME = "CONTACT_PERSON_NAME";
    public static final String CUSTOMER_ID = "customer_id";     //客户ID
    public static final String CUSTOMER_NAME = "customer_name";
    public static final String IS_CHOOSE_TYPE = "is_choose_type";
    public static final String PUMP_CAR_ID = "pump_car_id";
    public static final String CLIENT_MANAGEMENT_BEAN = "ClientManagementBean";
    public static final String BEAN = "bean";
    public static final String BEAN_TWO = "bean_two";
    public static final String BUNDLE = "bundle";
    public static final String ORDER_DETAIL_BEAN = "ORDER_DETAIL_BEAN";
    public static final String DEV_IDNO = "devIdno";
    public static final String DEV_NAME = "dev_name";
    public static final String ORDER_BEAN = "order_bean";
    public static final String PUMP_CAR_NAME = "pump_car_name";
    public static final String PUMP_CAR_CONTACT_PERSON = "pump_car_contact_person";
    public static final String NEED_RESULT = "needResult";
    public static final String PUMP_CAR_BEAN = "pump_car_bean";
    public static final String ORDER_SEARCH = "ORDER_SEARCH";
    public static final String CUSTOMER_NUMBER = "customer_number";
    public static final String PUMP_EMPLOYEE_ID = "pump_employee_id";
    public static final String PUMP_EMPLOYEE_NAME = "pump_employee_name";
    public static final String EDIT_POSITION = "edit_position";
    public static final String POSITION = "position";
    public static final String VIHICLE_INFO = "vihicle_info";
    public static final String VEHICLE_ID = "vehicleId";
    public static final String RE_INIT_TAB = "re_init_tab";
    public static final String ORDER_SEARCH_HAS_REFRESH = "ORDER_SEARCH_HAS_REFRESH";
    public static final String EDIT_IS_CHANGE_NAME = "edit_is_change_name";
    public static final String ADD_PROJECT_DATA = "add_project_data";

    public static final String addressSplitChar = " . ";
    public static final String ISVOICE="isVoice";
}
