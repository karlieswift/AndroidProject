package utils;


import java.util.ArrayList;

public class GetLines {


    public static ArrayList<ArrayList<String>> getLines(String strText) {


        ArrayList<ArrayList<String>> picilist = new ArrayList<ArrayList<String>>();


//        String str = "{'理科': [['计算机类（校本部）-581-579.0-43027', '自动化类（校本部）-581-578.0-43977', '电气工程及其自动化（校本部）-585-577.0-44925', '电子信息工程（校本部）-577-576.0-45897', '人工智能（校本部）-579-575.0-46947', '信息与计算科学（校本部）-574-574.0-47907', '交通运输（校本部）-579-573.0-48861', '光电信息科学与工程（校本部）-577-573.0-48861', '金融学（校本部）-576-573.0-48861', '机械设计制造及其自动化（校本部）-575-573.0-48861', '通信工程（校本部）-575-573.0-48861', '虚拟现实技术（校本部）-574-573.0-48861', '交通工程（校本部）-577-572.0-49827', '机械电子工程（校本部）-572-572.0-49827', '建筑学（学制五年）（校本部）-572-572.0-49827', '会计学（校本部）-575-571.0-50842', '测控技术与仪器（校本部）-574-571.0-50842', '智能制造工程（校本部）-572-571.0-50842', '给排水科学与工程（校本部）-571-570.0-51895', '测绘工程（校本部）-571-570.0-51895', '人力资源管理（校本部）-570-570.0-51895', '物流工程（校本部）-570-569.0-52925', '高分子材料与工程（校本部）-572-568.0-53926', '材料成型及控制工程（校本部）-570-568.0-53926', '铁道工程（校本部）-569-568.0-53926', '建筑电气与智能化（校本部）-569-568.0-53926', '环境工程（校本部）-569-568.0-53926', '土木工程（校本部）-572-567.0-54949', '物流管理（校本部）-570-567.0-54949', '车辆工程（校本部）-570-567.0-54949', '建筑环境与能源应用工程（校本部）-569-567.0-54949'], ['软件工程（铁道运输工程）-549-548.0-76893', '软件工程-544-542.0-84384', '软件工程（桥梁工程）-539-538.0-89520', '软件工程（轨道信号）-539-537.0-90783', '软件工程（道铁工程）-535-535.0-93448'], []]}";
        String[] split = strText.substring(1, strText.length() - 1).split(":");
        System.out.println(split[0].substring(1, 3));
        String s1 = split[1].trim();
        String s2 = s1.substring(1, s1.length() - 1);
        String[] split1 = s2.split("," + " " + "\\[");
        String[] pici = "一批,二批,专科批".split(",");

        ArrayList<String> lines_0 = new ArrayList<String>();
        int l_0 = split1[0].length();
        String[] split_0 = split1[0].substring(1, l_0 - 1).split(",");
        System.out.println(pici[0]);
        if (split_0.length!=0){

        }
        for (String s : split_0) {
            lines_0.add(s);
            System.out.println(s);
        }
        picilist.add(lines_0);



        ArrayList<String> lines_1 = new ArrayList<String>();
        int l_1 = split1[1].length();
        String[] split_1 = split1[1].substring(0, l_1 - 1).split(",");
        System.out.println(pici[1]);
        for (String s : split_1) {
            lines_1.add(s);
            System.out.println(s);
        }
        picilist.add(lines_1);
        ArrayList<String> lines_2 = new ArrayList<String>();
        int l_2 = split1[2].length();
        String[] split_2 = split1[1].substring(0, l_2 - 1).split(",");
        System.out.println(pici[2]);
        for (String s : split_2) {
            lines_2.add(s);
            System.out.println(s);
        }
        picilist.add(lines_2);

        return picilist;

    }


    //


}




 