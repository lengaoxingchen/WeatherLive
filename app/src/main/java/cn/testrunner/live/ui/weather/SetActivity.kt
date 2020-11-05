package cn.testrunner.live.ui.weather

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import cn.testrunner.live.MainActivity
import cn.testrunner.live.R
import cn.testrunner.live.WeatherLiveApplication
import cn.testrunner.live.ui.place.PlaceViewModel
import kotlinx.android.synthetic.main.activity_set.*

class SetActivity : AppCompatActivity(), View.OnClickListener {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set)

        //设置点击监听事件
        sharedTv.setOnClickListener(this)
        aboutTv.setOnClickListener(this)
        backIV.setOnClickListener(this)

        //设置版本信息
        val versionName = getVersionName()
        versionTv.text = "当前版本: V$versionName"
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.sharedTv -> {
                shareSoftWare("微天气App是一款超萌超可爱的天气预报软件,画面简约,播报天气情况精准,快来下载吧!")
            }

            R.id.aboutTv -> {
                Toast.makeText(this, "微天气App是一款超萌超可爱的天气预报软件,画面简约,播报天气情况精准", Toast.LENGTH_SHORT).show()
            }
            R.id.backIV -> {
                finish()
            }
        }
    }

    /**
     * 获取应用的版本信息
     */
    private fun getVersionName(): String {
        val manager = packageManager
        val packageInfo = manager.getPackageInfo(packageName, 0)
        return packageInfo.versionName
    }

    /**
     * 分享软件功能实现
     */
    private fun shareSoftWare(s: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plan"
        intent.putExtra(Intent.EXTRA_TEXT, s)
        startActivity(intent)
    }

}
