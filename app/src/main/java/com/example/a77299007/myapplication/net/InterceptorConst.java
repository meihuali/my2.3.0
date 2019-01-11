package com.example.a77299007.myapplication.net;

/**
 * Created by gongxueyong on 2018/4/23.
 */

public interface InterceptorConst {
    String ARGS_URL_NAME = "args_url_name";

    // base  路径 网关层的名字
    String RIBBON_API = "/ribbon-api/";

    // 搜索 路径 网关层的名字
    String SEARCH_FEIGN = "/search-feign/";


    String SEARCH_HEADER = ARGS_URL_NAME + ":" + SEARCH_FEIGN;


}
