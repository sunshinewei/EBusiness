package com.ediancha.edcbusiness.bean;

import com.dmz.library.dmzapi.api.bean.BaseListBean;
import com.dmz.library.dmzapi.api.bean.ViewBaseType;

/**
 * Created by dengmingzhi on 2017/9/22.
 */

public class SpaceOrderBean extends BaseListBean<SpaceOrderBean.Data> {
    public static class Data extends ViewBaseType {
        public String statusInfo;
        public String spaceName;
        public int orderStatus;
        public String[] info;
        public String orderId;

        public int getOrderStatus() {
            return orderStatus;
        }

        public String getOrderId() {
            return orderId;
        }

        public String getStatus() {
            return statusInfo;
        }

        public String getStatusInfo() {
            return statusInfo + "订单";
        }

        public String getTitle() {
            return spaceName;
        }

        public String getInfo() {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < info.length; i++) {
                sb.append(info[i]).append("\n");
            }
            return sb.substring(0, sb.length() - 1).toString();
        }

    }
}
