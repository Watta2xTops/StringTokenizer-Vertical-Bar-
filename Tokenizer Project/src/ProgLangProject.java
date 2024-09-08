import javax.swing.*;

public class ProgLangProject {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimplifiedJDialog dialog = new SimplifiedJDialog(new JFrame(), true);
            dialog.setTitle("String Tokenizer Program");
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        });
    }

    public static class SimplifiedJDialog extends JDialog {
        VerticalTokenizer tokens;


        private JButton endButton;
        private JButton startButton;
        private JScrollPane scrollPane;
        private JTextArea textArea;


        public SimplifiedJDialog(JFrame parent, boolean modal) {
            super(parent, modal);
            initializeComponents();
        }

        private void initializeComponents() {
            endButton = new JButton("End Program");
            startButton = new JButton("Start new text");
            textArea = new JTextArea(20, 5);
            scrollPane = new JScrollPane(textArea);

            endButton.addActionListener(evt -> onEndButtonClicked());
            startButton.addActionListener(evt -> setStartButtonClicked());
            setupLayout();

            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            pack();
        }

        private void setupLayout() {
            GroupLayout layout = new GroupLayout(getContentPane());
            getContentPane().setLayout(layout);

            layout.setHorizontalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                            .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 378, GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                    .addComponent(startButton)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(endButton)))
                                    .addContainerGap(16, Short.MAX_VALUE))
            );

            layout.setVerticalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGap(24, 24, 24)
                                    .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(endButton)
                                            .addComponent(startButton))
                                    .addGap(12, 12, 12))
            );
        }

        private void onEndButtonClicked() {
            System.exit(0);
        }
        private void setStartButtonClicked(){
            String userInput = JOptionPane.showInputDialog(null,"Please Enter the text");
            if (userInput != null) {
                tokens = new VerticalTokenizer(userInput);

                String tokenizedText = tokens.GetTokens();
                textArea.setText(tokenizedText);
            }
        }
    }
}
