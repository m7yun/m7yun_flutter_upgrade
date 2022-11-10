
import 'm7yun_flutter_upgrade_platform_interface.dart';

class M7yunFlutterUpgrade {
  Future showUpdateDialog(Map<String, dynamic> params) {
    return M7yunFlutterUpgradePlatform.instance.showUpdateDialog(params);
  }
}
