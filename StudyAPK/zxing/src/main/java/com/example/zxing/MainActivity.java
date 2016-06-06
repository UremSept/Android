package com.example.zxing;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.zxing.zxing.activity.CaptureActivity;
import com.example.zxing.zxing.encoding.EncodingHandler;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;

import java.util.Hashtable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final int CHOOSE_PIC = 0;
    private static final int PHOTO_PIC = 1;

    private EditText contentEditText = null;
    private ImageView qrcodeImageView = null;
    private String  imgPath = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
    }
    private void setupViews() {
        contentEditText = (EditText) findViewById(R.id.editText1);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        qrcodeImageView = (ImageView) findViewById(R.id.img1);
    }

    //������ά��ͼƬ,���ؽ����װ��Result������
    private com.google.zxing.Result  parseQRcodeBitmap(String bitmapPath){
        //����ת������UTF-8
        Hashtable<DecodeHintType, String> hints = new Hashtable<DecodeHintType, String>();
        hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
        //��ȡ����������ͼƬ
        BitmapFactory.Options options = new BitmapFactory.Options();
        //������ǰ�inJustDecodeBounds��Ϊtrue����ôBitmapFactory.decodeFile(String path, Options opt)
        //��������ķ���һ��Bitmap���㣬������������Ŀ���ȡ��������
        options.inJustDecodeBounds = true;
        //��ʱ��bitmap��null����δ���֮��options.outWidth �� options.outHeight����������Ҫ�Ŀ�͸���
        Bitmap bitmap = BitmapFactory.decodeFile(bitmapPath,options);
        //����������ȡ������ͼƬ�ı߳�����ά��ͼƬ�������εģ�����Ϊ400����
        /**
         options.outHeight = 400;
         options.outWidth = 400;
         options.inJustDecodeBounds = false;
         bitmap = BitmapFactory.decodeFile(bitmapPath, options);
         */
        //����������������Ȼ��bitmap�޶���������Ҫ�Ĵ�С�����ǲ�û�н�Լ�ڴ棬���Ҫ��Լ�ڴ棬���ǻ���Ҫʹ��inSimpleSize�������
        options.inSampleSize = options.outHeight / 400;
        if(options.inSampleSize <= 0){
            options.inSampleSize = 1; //��ֹ��ֵС�ڻ����0
        }
        /**
         * ������Լ�ڴ�����
         *
         * options.inPreferredConfig = Bitmap.Config.ARGB_4444;    // Ĭ����Bitmap.Config.ARGB_8888
         * options.inPurgeable = true;
         * options.inInputShareable = true;
         */
        options.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeFile(bitmapPath, options);
        //�½�һ��RGBLuminanceSource���󣬽�bitmapͼƬ�����˶���
        RGBLuminanceSource rgbLuminanceSource = new RGBLuminanceSource(bitmap);
        //��ͼƬת���ɶ�����ͼƬ
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(rgbLuminanceSource));
        //��ʼ����������
        QRCodeReader reader = new QRCodeReader();
        //��ʼ����
        Result result = null;
        try {
            result = reader.decode(binaryBitmap, hints);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return result;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imgPath = null;
        if(resultCode == RESULT_OK){
            switch (requestCode) {
                case CHOOSE_PIC:
                    String[] proj = new String[]{MediaStore.Images.Media.DATA};
                    Cursor cursor = MainActivity.this.getContentResolver().query(data.getData(), proj, null, null, null);

                    if(cursor.moveToFirst()){
                        int columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                        System.out.println(columnIndex);
                        //��ȡ���û�ѡ��Ķ�ά��ͼƬ�ľ���·��
                        imgPath = cursor.getString(columnIndex);
                    }
                    cursor.close();

                    //��ȡ�������
                    Result ret = parseQRcodeBitmap(imgPath);
                    Toast.makeText(MainActivity.this,"���������" + ret.toString(), Toast.LENGTH_LONG).show();
                    break;
                case PHOTO_PIC:
                    String result = data.getExtras().getString("result");
                    Toast.makeText(MainActivity.this,"���������" + result, Toast.LENGTH_LONG).show();
                    break;

                default:
                    break;
            }
        }

    }

    @SuppressLint("InlinedApi")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                //��ȡ�������������
                String content = contentEditText.getText().toString();
                //�ж������Ƿ�Ϊ��
                if (null == content || "".equals(content)) {
                    Toast.makeText(MainActivity.this, "������Ҫд���ά�������...",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    //���ɶ�ά��ͼƬ����һ�������Ƕ�ά������ݣ��ڶ���������������ͼƬ�ı߳�����λ������
                    Bitmap qrcodeBitmap = EncodingHandler.createQRCode(content, 400);
                    qrcodeImageView.setImageBitmap(qrcodeBitmap);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            case R.id.button2:
                //��ת��ͼƬѡ�����ȥѡ��һ�Ŷ�ά��ͼƬ
                Intent intent1 = new Intent();
//			if(android.os.Build.VERSION.SDK_INT < 19){
//				intent1.setAction(Intent.ACTION_GET_CONTENT);
//			}else{
//				intent1.setAction(Intent.ACTION_OPEN_DOCUMENT);
//			}
                intent1.setAction(Intent.ACTION_PICK);

                intent1.setType("image/*");

                Intent intent2 =  Intent.createChooser(intent1, "ѡ���ά��ͼƬ");
                startActivityForResult(intent2, CHOOSE_PIC);
                break;
            case R.id.button3:
                //��ת�����ս���ɨ���ά��
                Intent intent3 = new Intent(MainActivity.this, CaptureActivity.class);
                startActivityForResult(intent3, PHOTO_PIC);
                break;

            default:
                break;
        }

    }
}
