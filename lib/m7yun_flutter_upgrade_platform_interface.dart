import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'm7yun_flutter_upgrade_method_channel.dart';

abstract class M7yunFlutterUpgradePlatform extends PlatformInterface {
  /// Constructs a M7yunFlutterUpgradePlatform.
  M7yunFlutterUpgradePlatform() : super(token: _token);

  static final Object _token = Object();

  static M7yunFlutterUpgradePlatform _instance = MethodChannelM7yunFlutterUpgrade();

  /// The default instance of [M7yunFlutterUpgradePlatform] to use.
  ///
  /// Defaults to [MethodChannelM7yunFlutterUpgrade].
  static M7yunFlutterUpgradePlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [M7yunFlutterUpgradePlatform] when
  /// they register themselves.
  static set instance(M7yunFlutterUpgradePlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future showUpdateDialog(Map<String, dynamic> params) {
    throw UnimplementedError('showUpdateDialog() has not been implemented.');
  }
}
