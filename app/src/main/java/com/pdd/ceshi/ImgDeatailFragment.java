package com.pdd.ceshi;

import android.os.Bundle;
import android.webkit.WebView;

import com.pdd.ceshi.WeChatImage.CustomWebView;

public class ImgDeatailFragment extends BaseBizFragment {

    // 图文详情 WebView
    private CustomWebView mWebView;
//  List<String> list=new ArrayList<String>();
//  Adapter adapter;

    @Override
    protected int getFragmentResId() {
        return R.layout.product_detail_web_fragmet;
    }

    @Override
    protected void initUI() {


        mWebView = mRootView.findViewById(R.id.wv_good_detail_desc_webview);

        //自适应屏幕
//
//        Bundle data = getArguments();
//
//        String hs = data.getString("mark");
//        String[] hn = data.getStringArray("bigImage1");
//        String[] gd = data.getStringArray("bigImage0");
//
//        String PI_HNDRUGSCLASSIFICATION=data.getString("PI_HNDRUGSCLASSIFICATION");//处方分类
//        String PI_HNDRUGSCATEGORY=data.getString("PI_HNDRUGSCATEGORY");//成分类别
//        String PI_HNDRUGSINTENDED=data.getString("PI_HNDRUGSINTENDED");//适用人群
//        String PI_HNDRUGSDOSAGE=data.getString("PI_HNDRUGSDOSAGE");//用法用量
//        String PI_STORAGECODITION=data.getString("PI_STORAGECODITION");//储存条件
//        String PI_HNDRUGSDISEASE=data.getString("PI_HNDRUGSDISEASE");//适应症
//        String PI_HNDRUGSNAME=data.getString("PI_HNDRUGSNAME");//药品通用名
//        String pinname=data.getString("Pi_name");//药品名称

//        if (hs != null) {
//
//            String str = String.format("<html><head><style type=\"text/css\">*{margin:0 auto;padding:0;}html,body{font-size:62.5%%;width:100%%;}div,p,li,span,a,em,i,strong,b{font-size:3.2rem;}img{width:100%%;background:red;}</style></head><body><div>%s</div></body></html>", hs);
//            mWebView.loadDataWithBaseURL(null, str, "text/html", "utf-8", null);
//        } else if (hn != null) {
//
//            StringBuffer stringBuffer = new StringBuffer();
//            for (int i = 0; i < hn.length; i++) {
//                stringBuffer.append(toSpiltStr(hn[i]));
//            }
//
//            mWebView.getSettings().setUseWideViewPort(true);
//            mWebView.getSettings().setLoadWithOverviewMode(true);
//
//            String str = String.format("<html><head><style type=\"text/css\">*{margin:0 auto;padding:0;}html,body{font-size:62.5%%;width:100%%;}div,p,li,span,a,em,i,strong,b{font-size:3.2rem;}img{width:100%%;background:red;}</style></head><body><div>%s</div></body></html>",  stringBuffer.toString());
//            mWebView.loadDataWithBaseURL(null, str, "text/html", "utf-8", null);
//
//        } else if (gd != null) {
//            StringBuffer sBuffer = new StringBuffer();
//            for (int i = 0; i < gd.length; i++) {
//                sBuffer.append(toSpiltStr(gd[i]));
//            }
//
StringBuffer sBuffer = new StringBuffer();

            String head = "<html><head><style type=\"text/css\"></style></head>";
            String y=String.format("<body><table><tbody><tr><td >药品名称</td><td class=\"tdWidth\">%s</td></tr>","2222");
            String t=String.format("<tr><td>通用名称</td><td class=\"tdWidth\">%s</td></tr>","2222");
            String useStyle=String.format("<tr><td>用法用量</td><td class=\"tdWidth\">%s</td></tr>","2222");
            String ch=String.format("<tr><td >成分类别</td><td class=\"tdWidth\">%s</td></tr>","2222");
            String shiyongzheng=String.format("<tr><td>适应症</td><td class=\"tdWidth\">%s</td></tr>","2222");
            String storeCondition=String.format("<tr><td>储存条件</td><td class=\"tdWidth\">%s</td></tr>","2222");
            String usePeople=String.format("<tr><td>适用人群</td><td class=\"tdWidth\">%S</td></tr>","2222");
            String presCategary=String.format("<tr><td>处方分类</td><td class=\"tdWidth\">%s</td></tr></tbody></table>","2222");

            String pic=String.format("<div class=\"photos\">%s</div></body></html>",sBuffer.toString());
            String str=head+y+t+useStyle+ch+shiyongzheng+storeCondition+usePeople+presCategary+pic;
            mWebView.loadDataWithBaseURL(null, str, "text/html", "utf-8", null);

      //  }


    }

    /**
     * 图片拼路径地址
     *
     * @param str
     * @return
     */
    public String toSpiltStr(String str) {
        StringBuffer stringBuffer = new StringBuffer("<img src='' style='width:100%' />");
        int position = stringBuffer.indexOf("'", 0);
        stringBuffer = stringBuffer.insert(position + 1, str);
        return stringBuffer.toString();
    }

}
