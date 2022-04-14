package uz.egov.dpm.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Utils {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    private static final Logger logger = LogManager.getLogger(Utils.class);

    public static String date_format = "dd.MM.yyyy";
    public static String time_format = "dd.MM.yyyy hh:mm:ss";

    public static String cnty_list = "036;040;031;248;008;012;850;016;660;024;020;010;028;032;051;533;004;044;050;052;048;084;112;056;204;060;100;068;535;070;072;076;086;092;096;854;108;064;548;336;826;348;862;581;626;704;266;332;328;270;288;312;320;254;324;624;276;831;292;340;344;308;304;300;268;316;208;832;262;212;214;180;;818;894;732;716;376;356;360;400;368;364;372;352;724;380;887;132;398;136;116;120;124;634;404;196;417;296;158;408;156;166;170;174;188;384;192;414;531;418;428;426;430;422;434;440;438;442;480;478;450;175;446;807;454;458;466;462;470;504;474;584;484;583;508;498;492;496;500;104;516;520;524;562;566;528;558;570;554;540;578;784;512;074;833;184;574;162;612;654;586;585;275;591;598;600;604;616;620;630;178;410;638;643;646;642;222;882;674;678;682;748;580;690;652;663;666;686;670;659;662;688;702;534;760;703;705;090;706;729;740;840;694;762;764;834;796;768;772;776;780;798;788;795;792;800;860;804;876;858;234;242;608;246;238;250;258;260;334;191;140;148;499;203;152;756;752;744;144;218;226;232;233;231;710;239;728;388;392;";

    public static byte[] doSerialize(Object obj) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        byte[] res = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(obj);
            out.flush();
            res = bos.toByteArray();
        } catch (Exception e) {
            logger.error(e);
        } finally {
            try {
                bos.close();
            } catch (IOException ex) {
                logger.error(ex);
            }
        }
        return res;
    }

    public static Object doDeSerialize(byte[] bytes) {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInput in = null;
        Object res = null;
        try {
            in = new ObjectInputStream(bis);
            res = in.readObject();
        } catch (Exception e) {
            logger.error(e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                logger.error(ex);
            }
        }
        return res;
    }

    public static java.util.Date parseDate(String date) {
        try {
            return DATE_FORMAT.parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static java.util.Date parseTimestamp(String timestamp) {
        try {
            return new Timestamp(DATE_TIME_FORMAT.parse(timestamp).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static java.sql.Date convert(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

    public  static java.sql.Timestamp convertTimestamp(java.util.Date uDate) {
        java.sql.Timestamp sTimespamp = new java.sql.Timestamp(uDate.getTime());
        return sTimespamp;
    }
}
