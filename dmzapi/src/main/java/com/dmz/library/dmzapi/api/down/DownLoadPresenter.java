package com.dmz.library.dmzapi.api.down;

import com.dmz.library.dmzapi.api.DmzDownloadUtil;

import java.io.File;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by dengmingzhi on 2017/9/8.
 */

public class DownLoadPresenter {
    private IDownLoadView iDownLoadView;

    public DownLoadPresenter(IDownLoadView iDownLoadView) {
        this.iDownLoadView = iDownLoadView;
    }


    public void down(final Object tag, String url, String path, String name) {
        downBeans.add(new DownBean().setTag(tag).setName(name).setPath(path).setUrl(url));
        begin();
    }

    private int currentIndex = -1;

    private void begin() {
        currentIndex = currentIndex + 1;
        if (currentIndex == downBeans.size()) {
            currentIndex = -1;
            iDownLoadView.finish();
        } else {
            final DownBean downBean = downBeans.get(currentIndex);
            DmzDownloadUtil._instance(downBean.getUrl()).call(new DmzDownloadUtil.DmzFileCallBack(downBean.getPath(), downBean.getName()) {
                @Override
                public void onBefore(Request request, int id) {
                    super.onBefore(request, id);
                    iDownLoadView.start(downBean.getTag());
                }

                @Override
                public void onResponse(File response, int id) {
                    super.onResponse(response, id);
                    iDownLoadView.onSuccess(downBean.getTag());
                    begin();
                }

                @Override
                public void inProgress(float progress, long total, int id) {
                    super.inProgress(progress, total, id);
                    iDownLoadView.onProress(downBean.getTag(), progress);
                }

                @Override
                public void onError(Call call, Exception e, int id) {
                    super.onError(call, e, id);
                    iDownLoadView.onError(downBean.getTag());
                    begin();
                }
            });
        }

    }

    private ArrayList<DownBean> downBeans = new ArrayList<>();

    public int down(ArrayList datas) {
        downBeans.clear();
        currentIndex = -1;
        for (int i = 0; i < datas.size(); i++) {
            Object o = datas.get(i);
            if (o instanceof IDownIntenface) {
                IDownIntenface intenface = (IDownIntenface) o;
                String name = intenface.getName();
                String path = intenface.getPath();
                if (!new File(path, name).exists()) {
                    downBeans.add(new DownBean().setTag(intenface.getTag()).setName(name).setPath(path).setUrl(intenface.getUrl()));
                }
            }
        }
        begin();
        return downBeans.size();
    }

    public interface IDownIntenface {
        String getUrl();

        String getName();

        Object getTag();

        String getPath();

        void setTag(Object tag);
    }

    public static class DownBean {
        private String url;
        private String path;
        private String name;
        private Object tag;

        public DownBean setTag(Object tag) {
            this.tag = tag;
            return this;
        }

        public Object getTag() {
            return tag;
        }

        public DownBean setName(String name) {
            this.name = name;
            return this;
        }

        public DownBean setPath(String path) {
            this.path = path;
            return this;
        }

        public DownBean setUrl(String url) {
            this.url = url;
            return this;
        }

        public String getUrl() {
            return url;
        }

        public String getName() {
            return name;
        }

        public String getPath() {
            return path;
        }
    }
}
