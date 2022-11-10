import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'm7yun_flutter_upgrade_platform_interface.dart';

/// An implementation of [M7yunFlutterUpgradePlatform] that uses method channels.
class MethodChannelM7yunFlutterUpgrade extends M7yunFlutterUpgradePlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('m7yun_flutter_upgrade');

  @override
  Future showUpdateDialog(Map<String, dynamic> params) async {
    return methodChannel.invokeMethod<String>('showUpdateDialog', params);
  }
}
