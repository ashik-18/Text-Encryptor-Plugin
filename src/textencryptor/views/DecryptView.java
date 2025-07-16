package textencryptor.views;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import jakarta.annotation.PostConstruct;
import textencryptor.AESUtil;

public class DecryptView extends ViewPart {

	@PostConstruct
	public void createPartControl(final Composite parent) {
		parent.setLayout(new GridLayout(2, false));

		new Label(parent, SWT.NONE).setText("Enter encrypted text:");
		final Text inputText = new Text(parent, SWT.BORDER);
		inputText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		final Button decryptButton = new Button(parent, SWT.PUSH);
		decryptButton.setText("Decrypt");

		final Label outputLabel = new Label(parent, SWT.WRAP);
		outputLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		decryptButton.addListener(SWT.Selection, e -> {
			try {
				final String decrypted = AESUtil.encryptOrDecrypt(inputText.getText(), "decrypt");
				outputLabel.setText("Decrypted: " + decrypted);
			} catch (Exception ex) {
				outputLabel.setText("Decryption error");
			}
		});
	}

	@Focus
	public void setFocus() {

	}
}
