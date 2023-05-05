package Helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Helper {
	
	public static void optionPaneChangeButtonText() {
		UIManager.put("OptionPane.cancelButtontText", "İptal");
		UIManager.put("OptionPane.okButtontText", "Tamam");

		UIManager.put("OptionPane.yesButtontText", "Evet");
		UIManager.put("OptionPane.noButtontText", "Hayır");

	}

	public static void showMsg(String str) {
		String msg;
		optionPaneChangeButtonText();
		switch (str) {
		case "fill":
			msg = "Lütfen Tüm Alanları Doldurunuz !!";
			break;
		case "success":
			msg = "İşlem Başarılı !";
			break;
		case "error":
			msg = "bir hata oluştu  !";
			break;
			
		
		default:
			msg = str;
		}

		JOptionPane.showMessageDialog(null, msg, "Mesaj", JOptionPane.INFORMATION_MESSAGE);

	}

	public static boolean confirm(String str) {
		String msg;
		optionPaneChangeButtonText();
		switch (str) {
		case "sure":
			msg = "Bu işlemi gerçekleştirmek istiyor musun?";
			break;
		default:
			msg = str;
			break;
		}

		int res = JOptionPane.showConfirmDialog(null, msg, "Dikkat!", JOptionPane.YES_NO_OPTION);
		if (res == 0) {
			return true;
		} else {
			return false;
		}
	}
}
