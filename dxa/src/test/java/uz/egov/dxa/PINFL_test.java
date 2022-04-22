package uz.egov.dxa;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PINFL_test {
    public static void main(String[] args) {

        String index_pola_i_veka_rojdeniya = "3";
        String data_rojdeniya_ddMMyy = "121093";
        String kod_rayona_gorod = "204";
        String poryadkobiy_nomer_grajdanina = "024";

        index_pola_i_veka_rojdeniya = "4";
        data_rojdeniya_ddMMyy = "020190";
        kod_rayona_gorod = "205";
        poryadkobiy_nomer_grajdanina = "001";

        String kontrolnaya_ssifra = "";
        Integer century = 0
                , prm1 = 0
                , prm21 = 0
                , prm22 = 0
                , prm31 = 0
                , prm32 = 0
                , prm41 = 0
                , prm42 = 0
                , prm51 = 0
                , prm52 = 0
                , prm53 = 0
                , prm61 = 0
                , prm62 = 0
                , prm63 = 0;

        prm1 = Integer.parseInt(index_pola_i_veka_rojdeniya) * 7;

        prm21 = Integer.parseInt(data_rojdeniya_ddMMyy.substring(0, 1)) * 3;
        prm22 = Integer.parseInt(data_rojdeniya_ddMMyy.substring(1, 2)) * 1;

        prm31 = Integer.parseInt(data_rojdeniya_ddMMyy.substring(2, 3)) * 7;
        prm32 = Integer.parseInt(data_rojdeniya_ddMMyy.substring(3, 4)) * 3;

        prm41 = Integer.parseInt(data_rojdeniya_ddMMyy.substring(4, 5)) * 1;
        prm42 = Integer.parseInt(data_rojdeniya_ddMMyy.substring(5, 6)) * 7;

        prm51 = Integer.parseInt(kod_rayona_gorod.substring(0, 1)) * 3;
        prm52 = Integer.parseInt(kod_rayona_gorod.substring(1, 2)) * 1;
        prm53 = Integer.parseInt(kod_rayona_gorod.substring(2, 3)) * 7;

        prm61 = Integer.parseInt(poryadkobiy_nomer_grajdanina.substring(0, 1)) * 3;
        prm62 = Integer.parseInt(poryadkobiy_nomer_grajdanina.substring(1, 2)) * 1;
        prm63 = Integer.parseInt(poryadkobiy_nomer_grajdanina.substring(2, 3)) * 7;

        System.out.println(""
                + prm1 + "\t"
                + prm21 + ""
                + prm22 + "\t"
                + prm31 + ""
                + prm32 + "\t"
                + prm41 + ""
                + prm42 + "\t"
                + prm51 + ""
                + prm52 + ""
                + prm53 + "\t"
                + prm61 + ""
                + prm62 + ""
                + prm63 + ""
        );

        century = prm1 + prm21 + prm22 + prm31 + prm32 + prm41 + prm42 + prm51 + prm52 + prm53 + prm61 + prm62 + prm63;
        century = century % 10;
        kontrolnaya_ssifra = century + "";

        System.out.println("kontrolnaya_ssifra: " + kontrolnaya_ssifra);
    }
}
