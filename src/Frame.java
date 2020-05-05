import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Frame {
    private static final String APP_ID = "20200430000434502";
    private static final String SECURITY_KEY = "GkJSxqB3Nc1CwuDmStqt";
    String lan;

    public static void main(String[] args) {
        new Frame().surface();
    }

    public void surface() {

        JFrame jframe = new JFrame("translation");
        JFrame.setDefaultLookAndFeelDecorated(true);
        jframe.setBounds(300, 200, 400, 80);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      //关闭窗口

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());

        JLabel jLabel = new JLabel("请选择要转换的语言：");

        String[] str = {"英语", "中文", "粤语", "文言文", "日语", "韩语", "法语", "西班牙语", "泰语", "阿拉伯语", "俄语", "葡萄牙语", "德语", "意大利语", "希腊语", "荷兰语",
                "波兰语", "保加利亚语", "爱沙尼亚语", "丹麦语", "芬兰语", "捷克语", "罗马尼亚语", "斯洛文尼亚语", "瑞典语", "匈牙利语", "繁体中文", "越南语"};
        JComboBox<String> language = new JComboBox<>(str);
        language.addActionListener(e -> {
            lan = language.getSelectedItem().toString();
        });

        JTextArea input = new JTextArea(3, 30);
        JTextArea display = new JTextArea(3, 30);

        JButton tran = new JButton("翻译");
        tran.setBackground(Color.white);
        tran.addActionListener(e -> {
            String in = input.getText();
            if (in.equals("")) {
                JOptionPane.showMessageDialog(null, "输入不能为空！");
            } else {
                String out = (String) new Frame().buildType().get(lan);
                if (out == null)
                    out = "en";
                TransApi api = new TransApi(APP_ID, SECURITY_KEY);
                String string = api.getTransResult(in, "auto", out);
                JSONObject jsonObject = JSONObject.fromObject(string);
                JSONArray jsonArray = JSONArray.fromObject(jsonObject.getString("trans_result"));
                JSONObject jsonObject1 = JSONObject.fromObject(jsonArray.getString(0));
                display.setText(jsonObject1.getString("dst"));
            }
        });

        jPanel.add(input);
        jPanel.add(display);
        jPanel.add(tran);
        jPanel.add(jLabel);
        jPanel.add(language);

        jframe.setLayout(new FlowLayout());
        jframe.add(jPanel);
        jframe.pack();
        jframe.setVisible(true);
    }

    public Map buildType() {
        Map type = new HashMap();
        type.put("中文", "zh");
        type.put("英语", "en");
        type.put("粤语", "yue");
        type.put("文言文", "wyw");
        type.put("日语", "jp");
        type.put("韩语", "kor");
        type.put("法语", "fra");
        type.put("西班牙语", "spa");
        type.put("泰语", "th");
        type.put("阿拉伯语", "ara");
        type.put("俄语", "ru");
        type.put("葡萄牙语", "pt");
        type.put("德语", "de");
        type.put("意大利语", "it");
        type.put("希腊语", "el");
        type.put("荷兰语", "nl");
        type.put("波兰语", "pl");
        type.put("保加利亚语", "bul");
        type.put("爱沙尼亚语", "est");
        type.put("丹麦语", "dan");
        type.put("芬兰语", "fin");
        type.put("捷克语", "cs");
        type.put("罗马尼亚语", "rom");
        type.put("斯洛文尼亚语", "slo");
        type.put("瑞典语", "swe");
        type.put("匈牙利语", "hu");
        type.put("繁体中文", "cht");
        type.put("越南语", "vie");
        return type;
    }
}
