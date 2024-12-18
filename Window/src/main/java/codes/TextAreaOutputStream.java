package codes;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;

public class TextAreaOutputStream extends OutputStream 
{
        private final JTextArea textArea;

        public TextAreaOutputStream(JTextArea textArea) 
        {
            this.textArea = textArea;
        }

        @Override
        public void write(int b) throws IOException 
        {
            textArea.append(String.valueOf((char) b));
            textArea.setCaretPosition(textArea.getDocument().getLength());  // Rola para a última linha
        }

        @Override
        public void write(byte[] b, int off, int len) throws IOException 
        {
            textArea.append(new String(b, off, len));
            textArea.setCaretPosition(textArea.getDocument().getLength());
        }
    }

