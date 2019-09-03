package com.dicoding.basicfree.recipenotes.model;

import java.util.ArrayList;

public class RecipeDatas
{
    public static String[][] datas = new String[][]{
            {"COTO MAKASSAR","cotomakassar.txt","https://scontent-sin2-2.cdninstagram.com/vp/c74e9063ade97d822fb7f5f1bb40d1d2/5E084C7A/t51.2885-15/sh0.08/e35/s640x640/12907124_1364309910261689_568272854_n.jpg?_nc_ht=scontent-sin2-2.cdninstagram.com&_nc_cat=105"},
            {"CHOCOLATE OGURA CAKE SUPER SOFT","chocolateoguracake.txt","https://scontent-sin2-2.cdninstagram.com/vp/2b8b78232622e4c5df5e38b5465447e4/5E106B5A/t51.2885-15/e35/12976397_854275484701949_642586136_n.jpg?_nc_ht=scontent-sin2-2.cdninstagram.com&_nc_cat=105"},
            {"ROTI SISIR COKELAT","rotisisircokelat.txt","https://scontent-sin2-2.cdninstagram.com/vp/ef0814579025bbd04565010b605b3704/5E026EAF/t51.2885-15/sh0.08/e35/p640x640/38081067_233329263991450_2939244307623706624_n.jpg?_nc_ht=scontent-sin2-2.cdninstagram.com&_nc_cat=110"},
            {"TUMIS MAKARONI SOSIS","tumismakaronisosis.txt","https://scontent-sin2-2.cdninstagram.com/vp/13e8e41b64d709251e16360dd6ce344d/5E0E0101/t51.2885-15/sh0.08/e35/s640x640/66808501_221513112152567_1766542462419659724_n.jpg?_nc_ht=scontent-sin2-2.cdninstagram.com&_nc_cat=111"},
            {"SATE TAICHA ala DEE","satetaichanaladee.txt","https://scontent-sin2-2.cdninstagram.com/vp/2ff15db32dc81d19f2d80c8adef876c8/5DFBEF65/t51.2885-15/sh0.08/e35/s640x640/67060510_924358231237013_5667527755495498122_n.jpg?_nc_ht=scontent-sin2-2.cdninstagram.com&_nc_cat=105"},
            {"CAPPUCHINO CINCAU","cappuchinocincau.txt","https://scontent-sin2-2.cdninstagram.com/vp/cbea24e48e5f707ded290ec507a94fe1/5DF2F5B6/t51.2885-15/sh0.08/e35/p640x640/64765577_465462710687765_3603552785373647497_n.jpg?_nc_ht=scontent-sin2-2.cdninstagram.com&_nc_cat=105"},
            {"BOLU SAKURAN","bolusakura.txt","https://scontent-sin2-2.cdninstagram.com/vp/54924d0c7d1a9ea2536a8e5ff744e35b/5DF626FA/t51.2885-15/sh0.08/e35/p640x640/65055350_182441496095541_1022203109885368754_n.jpg?_nc_ht=scontent-sin2-2.cdninstagram.com&_nc_cat=109"},
            {"BOLU GULUNG","bolugulung.txt","https://scontent-sin2-2.cdninstagram.com/vp/1b48b943d7767fc20ed9821e32ce75fe/5E080743/t51.2885-15/sh0.08/e35/s640x640/44248996_268401073877957_2820367136869369581_n.jpg?_nc_ht=scontent-sin2-2.cdninstagram.com&_nc_cat=105"},
            {"LAPIS LEGIT PANDAN","lapislegitpandan.txt","https://scontent-sin2-2.cdninstagram.com/vp/5a5e7953d52bba6edc30199f50f1832a/5DFFF88E/t51.2885-15/sh0.08/e35/s640x640/66271535_881169335591162_867717568056608089_n.jpg?_nc_ht=scontent-sin2-2.cdninstagram.com&_nc_cat=102"},
            {"PUDING COKELAT ala KFC","pudingcokelatalakfc.txt","https://scontent-sin2-2.cdninstagram.com/vp/31c5612d7cc8e21a5125137cb48202e4/5E014085/t51.2885-15/sh0.08/e35/s640x640/66262023_2091138994330964_8077871802278595704_n.jpg?_nc_ht=scontent-sin2-2.cdninstagram.com&_nc_cat=108"}
    };

    public static ArrayList<Datas> getListData()
    {
        ArrayList<Datas> list = new ArrayList<>();
        for (String[] aData : datas)
        {
            Datas datas = new Datas();
            datas.setJudul(aData[0]);
            datas.setDetails(aData[1]);
            datas.setPhoto(aData[2]);

            list.add(datas);
        }
        return list;
    }
}
