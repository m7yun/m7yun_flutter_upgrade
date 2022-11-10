package com.m7yun.m7yun_flutter_upgrade;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSONObject;
import com.azhon.appupdate.config.UpdateConfiguration;
import com.azhon.appupdate.manager.DownloadManager;

import java.util.Map;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

/** M7yunFlutterUpgradePlugin */
public class M7yunFlutterUpgradePlugin implements FlutterPlugin, ActivityAware, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private MethodChannel channel;

  private Activity mActivity;

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "m7yun_flutter_upgrade");
    channel.setMethodCallHandler(this);
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if (call.method.equals("showUpdateDialog")) {
      showUpdateDialog(call, result);
    } else {
      result.notImplemented();
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }

  @Override
  public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
    mActivity = binding.getActivity();
  }

  @Override
  public void onDetachedFromActivityForConfigChanges() {

  }

  @Override
  public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {

  }

  @Override
  public void onDetachedFromActivity() {

  }

  public void showUpdateDialog(MethodCall call, Result result)
  {
    DownloadManager downloadManager = DownloadManager.getInstance(mActivity);
    JSONObject params = new JSONObject((Map<String, Object>) call.arguments);
    Log.v("showUpdateDialog", params.toJSONString());
    String apkUrl = params.getString("packageUrl");
    String apkName = System.currentTimeMillis() + ".apk";
    int apkVersionCode = params.getInteger("versionCode");
    String version = params.getString("version");
    String desc = params.getString("desc");
    Boolean force = params.getBoolean("force");
    Boolean showNewVersion = params.getBoolean("showNewVersion");
    downloadManager.setApkUrl(apkUrl);
    downloadManager.setApkName(apkName);
    downloadManager.setSmallIcon(mActivity.getResources().getIdentifier("ic_launcher", "mipmap", mActivity.getApplication().getPackageName()));
    if (apkVersionCode > 0) {
      downloadManager.setApkVersionCode(apkVersionCode);
    }
    if (version != null) {
      downloadManager.setApkVersionName(version);
    }
    if (desc != null) {
      downloadManager.setApkDescription(desc);
    }
    UpdateConfiguration updateConfiguration = new UpdateConfiguration();
    if (force != null && force) {
      updateConfiguration.setForcedUpgrade(true);
    }
    if (showNewVersion != null && showNewVersion) {
      downloadManager.setShowNewerToast(true);
    }
    downloadManager.setConfiguration(updateConfiguration);
    downloadManager.download();
  }
}
