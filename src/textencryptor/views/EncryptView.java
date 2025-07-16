package textencryptor.views;

import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
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

public class EncryptView extends ViewPart {

	@PostConstruct
	public void createPartControl(final Composite parent) {

		parent.setLayout(new GridLayout(2, false));
		final Clipboard clipboard = new Clipboard(parent.getDisplay());

		new Label(parent, SWT.NONE).setText("Enter text:");
		final Text inputText = new Text(parent, SWT.BORDER);
		inputText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		final Button encryptButton = new Button(parent, SWT.PUSH);
		encryptButton.setText("Encrypt");
		final Label outputText = new Label(parent, SWT.WRAP);
		outputText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		final Button copyButton = new Button(parent, SWT.PUSH);
		copyButton.setText("Copy to Clipboard");
		copyButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));

		encryptButton.addListener(SWT.Selection, e -> {
			try {
				final String encrypted = AESUtil.encryptOrDecrypt(inputText.getText(), "encrypt");
				outputText.setText("Encrypted: " + encrypted);

			} catch (Exception ex) {
				outputText.setText("Encryption error");
			}
		});
		copyButton.addListener(SWT.Selection, e -> {
			final String result = outputText.getText();
			if (!result.isEmpty() && result.startsWith("Encrypted: ")) {
				final String encryptedOnly = result.replace("Encrypted: ", "");
				clipboard.setContents(new Object[] { encryptedOnly }, new Transfer[] { TextTransfer.getInstance() });
			}
		});

	}

	@Focus
	public void setFocus() {
	}
}
