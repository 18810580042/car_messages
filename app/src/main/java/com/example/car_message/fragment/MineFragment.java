package com.example.car_message.fragment;


import android.Manifest;
import android.annotation.TargetApi;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.graphics.drawable.BitmapDrawable;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Base64;

import android.view.Gravity;

import android.view.View;

import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.car_message.R;
import com.example.car_message.activity.ChangePassActivity;
import com.example.car_message.activity.SelfMessageActivity;
import com.example.car_message.adapter.MinervAdapter;

import com.example.car_message.base.BaseFragment;


import java.io.ByteArrayInputStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment implements View.OnClickListener {
    public static final int TAKE_PHOTO = 1;
    public static final int SELECT_PHOTO = 2;
    private ImageView mImgHeadMine;
    private TextView mTextHeadnameMine;
    private RecyclerView mRvMine;
    private MinervAdapter minervAdapter;
    //假数据
    List<String> listdata = new ArrayList<>();
    int[] img = {R.mipmap.space_seven,
            R.mipmap.space_eight,
            R.mipmap.space_nine};
    String[] name = {"出勤统计", "驾驶行为", "系统设置"};
    private FragmentActivity activity;
    private TextView mTextheadmathMine;
    private static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    /* 头像名称 */
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private File tempFile;
    private PopupWindow popupWindow;

    private Uri imageUri;
    private Button outof_login;

    @Override
    protected int getLayoutId() {
        if (listdata.size() == 0) {
            for (int i = 0; i < 3; i++) {
                listdata.add(name[i]);
            }
        } else {

        }
        return R.layout.fragment_mine;
    }

    @Override
    protected void init(View view, Bundle savedInstanceState) {
        mImgHeadMine = (ImageView) view.findViewById(R.id.img_head_mine);//头像
        mTextHeadnameMine = (TextView) view.findViewById(R.id.text_headname_mine);//账号
        mTextheadmathMine = view.findViewById(R.id.text_headmath_mine); //天数
        View head_rl = view.findViewById(R.id.mine_head_rl);
        head_rl.setOnClickListener(this);
        //退出登录
        outof_login = view.findViewById(R.id.mine_outof_login);
        outof_login.setOnClickListener(this);
        mImgHeadMine.setOnClickListener(this);
        mRvMine = (RecyclerView) view.findViewById(R.id.rv_mine);
        ImageView img_back = view.findViewById(R.id.tal_img_back);
        img_back.setVisibility(View.GONE);
        TextView textshow = view.findViewById(R.id.tal_text_show);
        textshow.setText("我的");
        activity = getActivity();
        minervAdapter = new MinervAdapter(getContext(), listdata, img);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRvMine.setLayoutManager(linearLayoutManager);
        mRvMine.setAdapter(minervAdapter);
        minervAdapter.setMineClick(new MinervAdapter.MineClick() {
            @Override
            public void ShowMine(int position) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        Intent intent = new Intent(getActivity(), ChangePassActivity.class);
                        startActivity(intent);
                        break;
                }
                Toast.makeText(activity, "数据" + position, Toast.LENGTH_SHORT).show();
            }
        });
        minervAdapter.notifyDataSetChanged();
        //从SharedPreferences获取图片
        getBitmapFromSharedPreferences();

    }


    @Override
    protected void loadData() {

    }

    @Override
    public void setParams(Bundle bundle) {

    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onReloading(String type) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_head_mine:
                HideBackgroun();
                break;
            case R.id.btn_take_photo://拍照
                popupWindow.dismiss();
                //拍照获取图片
                take_photo();
//                // 激活相机
//                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
//                // 判断存储卡是否可以用，可用进行存储
//                if (hasSdcard()) {
//                    tempFile = new File(Environment.getExternalStorageDirectory(), PHOTO_FILE_NAME);
//                    // 从文件中创建uri
//                    Uri uri = Uri.fromFile(tempFile);
//                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
//                }
//                // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA
//                startActivityForResult(intent, PHOTO_REQUEST_CAREMA);
                break;
            case R.id.btn_pick_photo://相册
                popupWindow.dismiss();
//                // 激活系统图库，选择一张图片
//                Intent intent1 = new Intent(Intent.ACTION_PICK);
//                intent1.setType("image/*");
//                // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
//                startActivityForResult(intent1, PHOTO_REQUEST_GALLERY);
                //从相册中选取图片
                select_photo();

                break;
            case R.id.btn_cancel://取消
                popupWindow.dismiss();
                break;
            case R.id.mine_outof_login:
                //退出登录
                OutofLogin();
                break;
            case R.id.mine_head_rl:
                Intent intent=new Intent(getActivity(), SelfMessageActivity.class);
                startActivity(intent);
                break;
        }
    }

    //退出登录 操作
    private void OutofLogin() {
        Toast.makeText(activity, "退出登录", Toast.LENGTH_SHORT).show();
    }

    /*
     * 判断sdcard是否被挂载
     */
    private boolean hasSdcard() {
        //判断ＳＤ卡手否是安装好的　　　media_mounted
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(activity.getContentResolver().openInputStream(imageUri));
                        mImgHeadMine.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case SELECT_PHOTO:
                if (resultCode == RESULT_OK) {
                    //判断手机系统版本号
                    if (Build.VERSION.SDK_INT > 19) {
                        //4.4及以上系统使用这个方法处理图片
                        handleImgeOnKitKat(data);
                    } else {
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                break;

        }
    }


    //从SharedPreferences获取图片
    private void getBitmapFromSharedPreferences() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("testSP", Context.MODE_PRIVATE);
        //第一步:取出字符串形式的Bitmap
        String imageString = sharedPreferences.getString("image", "");
        //第二步:利用Base64将字符串转换为ByteArrayInputStream
        byte[] byteArray = Base64.decode(imageString, Base64.DEFAULT);
        if (byteArray.length == 0) {
            mImgHeadMine.setImageResource(R.drawable.iv_no_scan_data);
        } else {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);

            //第三步:利用ByteArrayInputStream生成Bitmap
            Bitmap bitmap = BitmapFactory.decodeStream(byteArrayInputStream);
            mImgHeadMine.setImageBitmap(bitmap);
        }

    }


    private void HideBackgroun() {
        // 产生背景变暗效果
        final View contentView = View.inflate(getActivity(), R.layout.phone_picture, null);

        changeWindowAlfa(0.7f);//改变窗口透明度
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchable(true);//设置窗口触摸
        popupWindow.setFocusable(true);
        popupWindow.setAnimationStyle(R.style.pop_shop_anim);//设置popupwindow弹出动画
        popupWindow.setOutsideTouchable(true);//设置窗口外部可触摸   要结合setBackgroundDrawable来使用
        //设置popupwindow背景
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);//相对于父窗体底部
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            // 在dismiss中恢复透明度
            public void onDismiss() {
                popupWindow.dismiss();
                changeWindowAlfa(1f);
            }
        });
        Button take_phone = contentView.findViewById(R.id.btn_take_photo);
        Button pick_phone = contentView.findViewById(R.id.btn_pick_photo);
        Button cancel = contentView.findViewById(R.id.btn_cancel);
        take_phone.setOnClickListener(this);
        pick_phone.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    /*
       更改屏幕窗口透明度
    */
    void changeWindowAlfa(float alfa) {
        WindowManager.LayoutParams params = getActivity().getWindow().getAttributes();
        params.alpha = alfa;
        getActivity().getWindow().setAttributes(params);
    }

    /**
     * 拍照获取图片
     **/
    public void take_photo() {

        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            //创建File对象，用于存储拍照后的图片
            File outputImage = new File(activity.getExternalCacheDir(), "output_image.jpg");
            try {
                if (outputImage.exists()) {
                    outputImage.delete();
                }
                outputImage.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (Build.VERSION.SDK_INT >= 24) {
                imageUri = FileProvider.getUriForFile(activity, "com.example.car_message.fragment.MineFragment", outputImage);
            } else {
                imageUri = Uri.fromFile(outputImage);
            }
            //启动相机程序
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(intent, TAKE_PHOTO);
        } else {

            Toast.makeText(activity, "没有储存卡", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 从相册中获取图片
     */
    public void select_photo() {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            openAlbum();
        }
    }

    /**
     * 打开相册的方法
     */
    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, SELECT_PHOTO);
    }

    /**
     * 4.4以下系统处理图片的方法
     */
    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    /**
     * 4.4及以上系统处理图片的方法
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void handleImgeOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(activity, uri)) {
            //如果是document类型的uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                //解析出数字格式的id
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            } else if ("content".equalsIgnoreCase(uri.getScheme())) {
                //如果是content类型的uri，则使用普通方式处理
                imagePath = getImagePath(uri, null);
            } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                //如果是file类型的uri，直接获取图片路径即可
                imagePath = uri.getPath();
            }
            //根据图片路径显示图片
            displayImage(imagePath);
        }
    }

    /**
     * 根据图片路径显示图片的方法
     */
    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            mImgHeadMine.setImageBitmap(bitmap);
        } else {
            Toast.makeText(activity, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 通过uri和selection来获取真实的图片路径
     */
    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = activity.getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(activity, "failed to get image", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}
