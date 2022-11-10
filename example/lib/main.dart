import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:m7yun_flutter_upgrade/m7yun_flutter_upgrade.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  final _m7yunFlutterUpgradePlugin = M7yunFlutterUpgrade();

  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: InkWell(
            onTap: () {
              Map<String, dynamic> params = {
                'version': '1.0.1',
                'versionCode': 2,
                'packageUrl': 'https://imtt.dd.qq.com/sjy.20002/16891/apk/BD063E16C38AC4C5C3AE512A21FB0E31.apk?fsname=com.m7yun.roof_2.1.4_27.apk&hsr=4d5s',
                'desc': '更新内容',
                'force': true
              };
              _m7yunFlutterUpgradePlugin.showUpdateDialog(params);
            },
            child: Text('Running on'),
          ),
        ),
      ),
    );
  }
}
