package id.my.levirs.prakpbotugas1;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.swing.*;

/**
 *
 * @author levir
 */
public class MainFrame extends JFrame {
    private JLabel mNamaDepanLabel, mNamaBelakangLabel, mJenisKelaminLabel, mMessageLabel, mBottomLabel;
    private JTextField mNamaDepanField, mNamaBelakangField;
    private JRadioButton mPriaRadio, mWanitaRadio;
    private ButtonGroup mButtonGroup;
    private JButton mSimpanButton, mConvertButton;
    private JScrollPane mScrollPane;
    private JTextArea mTextArea;
    private ArrayList<Orang> mData;
    
    public MainFrame() { 
        setLayout(new GridBagLayout());
        
        var constraint = new GridBagConstraints();
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.anchor = GridBagConstraints.LINE_START;
        constraint.insets = new Insets(5, 5, 5, 5);
        
        mNamaDepanLabel = new JLabel("Nama Depan:");
        add(mNamaDepanLabel, constraint);
        
        constraint.gridx = 1;
        mNamaBelakangLabel = new JLabel("Nama Belakang:");
        add(mNamaBelakangLabel, constraint);
        
        constraint.insets.top = 0;
        
        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 1;
        mNamaDepanField = new JTextField();
        add(mNamaDepanField, constraint);
        
        constraint.gridx = 1;
        mNamaBelakangField = new JTextField();
        add(mNamaBelakangField, constraint);
        
        constraint.fill = GridBagConstraints.NONE;
        constraint.weightx = 0;
        constraint.gridx = 0;
        constraint.gridy = 2;
        constraint.gridwidth = 2;
        
        mJenisKelaminLabel = new JLabel("Jenis Kelamin:");
        add(mJenisKelaminLabel, constraint);
        
        constraint.gridy = 3;
        constraint.gridwidth = 1;
        constraint.anchor = GridBagConstraints.LINE_END;
        mPriaRadio = new JRadioButton("Pria");
        add(mPriaRadio, constraint);
        
        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        mWanitaRadio = new JRadioButton("Wanita");
        add(mWanitaRadio, constraint);
        
        mButtonGroup = new ButtonGroup();
        mButtonGroup.add(mPriaRadio);
        mButtonGroup.add(mWanitaRadio);
        
        constraint.gridy = 4;
        constraint.gridx = 0;
        constraint.gridwidth = 2;
        constraint.fill = GridBagConstraints.HORIZONTAL;
        mSimpanButton = new JButton("Simpan");
        add(mSimpanButton, constraint);
        
        constraint.gridy = 5;
        mMessageLabel = new JLabel();
        mMessageLabel.setPreferredSize(new Dimension(150, 25));
        add(mMessageLabel, constraint);
        
        constraint.gridy = 6;
        constraint.fill = GridBagConstraints.BOTH;
        constraint.weighty = 1;
        constraint.weightx = 1;
        mTextArea = new JTextArea();
        mTextArea.setEditable(false);
        
        mScrollPane = new JScrollPane(mTextArea);
        mScrollPane.setPreferredSize(new Dimension(300, 200));
        add(mScrollPane, constraint);
        
        constraint.gridy = 7;
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weighty = 0;
        mConvertButton = new JButton("Convert to .txt file");
        add(mConvertButton, constraint);
        
        constraint.gridy = 8;
        mBottomLabel = new JLabel();
        mBottomLabel.setPreferredSize(new Dimension(150, 25));
        add(mBottomLabel, constraint);
        
        pack();
        
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Input Data");
        setLocationRelativeTo(null);
        
        mData = new ArrayList<Orang>();
        
        mSimpanButton.addActionListener((e) -> {
            if (mNamaDepanField.getText().isEmpty()) {
                mMessageLabel.setText("Nama Depan Tidak Boleh Kosong");
                return;
            }
            if (mNamaBelakangField.getText().isEmpty()) {
                mMessageLabel.setText("Nama Depan Tidak Boleh Kosong");
                return;
            }
            Orang.Gender gender = null;
            if (mPriaRadio.isSelected()) gender = Orang.Gender.PRIA;
            else if (mWanitaRadio.isSelected()) gender = Orang.Gender.WANITA;
            
            if (gender == null) {
                 mMessageLabel.setText("Gender Tidak Boleh Kosong");
                 return;
            }
            
            mData.add(new Orang(
                    mNamaDepanField.getText(), 
                    mNamaBelakangField.getText(), 
                    gender)
            );
            mTextArea.setText(getDataString());
            
            mNamaDepanField.setText("");
            mNamaBelakangField.setText("");
            mButtonGroup.clearSelection();
            mMessageLabel.setText("");
        });
        
        mConvertButton.addActionListener((e) -> {
            if (mData.isEmpty()) {
                mBottomLabel.setText("Data masih kosong");
                return;
            }
            
            mBottomLabel.setText("");
            
            try {
                mTextArea.write(new FileWriter("data_nim.txt"));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Penyimpanan file gagal", "Gagal", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            JOptionPane.showMessageDialog(this, "Penyimpanan file berhasil", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
                
        });
    }
    
    private String getDataString() {
        return mData.stream()
            .map((orang) -> orang.asString())
            .collect(Collectors.joining("\n"));
    }
}
