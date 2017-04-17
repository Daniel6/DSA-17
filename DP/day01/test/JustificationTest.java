import org.junit.Test;
import org.junit.Before;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class JustificationTest {

    @Test
    public void testJustifyText1() {
        List<Integer> soln = Arrays.asList(0, 3, 6);
        int m = 16;
        String[] w = {"This", "is", "an", "example", "of", "text", "justification"};
        assertThat(TextJustification.justifyText(w, m), is(soln));
    }

    @Test
    public void testJustifyText2() {
        List<Integer> soln = Arrays.asList(0, 2, 4);
        int m = 15;
        String[] w = {"blah", "blah", "blah", "blah", "reallylongword"};
        assertThat(TextJustification.justifyText(w, m), is(soln));
    }


    @Test
    public void testJustifyText3() {
        List<Integer> soln = Arrays.asList(0, 2, 4, 5, 6, 7, 9);
        int m = 12;
        String[] w = {"hieu", "and", "sidd", "are", "really", "really", "really", "good", "at", "rocketleague"};
        assertThat(TextJustification.justifyText(w, m), is(soln));
    }


    @Test
    public void testJustifyText4() {
        List<Integer> soln = Arrays.asList(0, 2, 4, 5, 7, 9, 11, 13, 15, 17, 19, 21, 22, 23, 25, 28, 30, 32, 34, 35, 36, 39, 43, 45, 48, 50, 51, 52, 54, 56, 58, 59, 61, 63, 64, 65, 67, 68, 70, 72, 75, 77, 78, 80, 83, 85, 87, 89, 91, 93, 95, 97, 98, 100, 103, 105, 107, 109, 111, 113, 115, 118, 121, 123, 125, 128, 130, 131, 133, 135, 137, 140, 142, 144, 147, 148, 150, 151, 154, 156, 159, 162, 164, 165, 167, 169, 171, 173, 175, 177, 179, 181, 183, 185, 187, 190, 192, 194, 195, 197, 199, 201, 203, 205, 206, 208, 210, 211, 213, 214, 215, 217, 219, 221, 223, 225, 227, 228, 230, 232, 234, 236, 238, 240, 242, 244, 246, 247, 249, 251, 253, 255, 257, 258, 260, 263, 266, 268, 270, 272, 274, 276, 278, 280, 282, 284, 286, 289, 291, 293, 295, 297, 300, 302, 304, 306, 307, 309, 312, 314, 316, 317, 319, 321, 323, 325, 327, 329, 332, 334, 337, 339, 341, 343, 345, 347, 350, 352, 354, 356, 358, 360, 362, 365, 367, 369, 372, 374, 375, 376, 377, 378, 380, 382, 384, 386, 388, 390, 393, 395, 396, 397, 399, 401, 403, 405, 407, 408, 409, 411, 414, 416, 418, 420, 422, 425, 427, 430, 432, 434, 436, 437, 439, 441, 443, 445, 447, 449, 451, 453, 456, 458, 460, 461, 462, 463, 465, 467, 469, 472, 474, 475, 477, 478, 479, 480, 482, 485, 487, 489, 491, 493, 496, 498);
        int m = 18;
        String[] w = {"dcehtdp","oem","qeleqn","sbrbmizrva","yjvnmenad","ong","nfvqjedts","rrqmbce","dnxc","sryrcs","dajqwerj","dxxratfv","syy","ncpgqa","lprhsiiiu","istjlcnlog","yeuyz","scegzvzjyq","cqb","umpmhfv","vdaumimx","ezqozzlwsr","pcsgnxswa","nqa","pgius","fbx","cjv","falrb","yojetsty","ntzravecy","vloqqd","emsfl","mcmvbmcy","jlzyjzfep","inikwmdkhg","ccglewsjv","diqx","nfb","cgzpwc","rxgc","dgi","vvgl","codh","wuc","afueabblxd","kbtdgig","aqaxg","gkch","mlrzmf","zmgirckzq","weqlqfxos","maoeuxpmzr","gnnua","akpydcqxvh","ahrhpjpnm","xot","gcrqnulo","jypnrvbv","qqbbllphzb","clmellsawi","kfrvd","yxyvcq","cdmlguvk","pytiiytuco","rtpnwymi","fpjvkwp","pxaiwvjebc","fwthoqyl","dgviv","pbxc","aozjedymd","saykbfs","ltiq","zxuu","gkxvtx","qthcrza","ojxudnnjdl","bigkblqwah","svmzyciw","uzk","fmxhii","wtkwu","rpp","lyfrug","bahjpgjv","dxfp","xvyvhnqnk","ghac","ibcyjj","ydhm","aqsccsps","hnepwszl","ffdau","shykjcnc","dkvyb","ebetmf","avvrkjhr","yplnairvfh","kdtep","vwr","cca","bjzani","otlnmq","mfkxrvmjwv","zusyuds","jnodlwdgac","ofifbo","tzpeltsd","ffmg","ovy","bcfpecdwop","teywloo","hoklz","tmvctn","knoogfcg","qsqpbm","yrf","yxafra","clq","gll","vxpxqgpsih","ccccueu","cqvfkbpc","hdhziz","pstyocap","gcdwypefq","wmxg","lll","cuqgmv","wtywmctyjr","kmluxyup","rnv","narfehbibr","mfbawg","psjtvusc","izgrbsp","bxucxcwc","nvv","nzzthj","dwdtwv","xhjpgdju","hiuajuvp","wixkljpt","bgr","ogh","lfl","bylsgihdv","liwpoauvn","peo","zpbcyelua","zhamfxypnk","nbvr","htfobn","qwiduh","qbcphpfba","uwwcyq","xqu","lzbc","qxq","kxmxy","vgb","rsdjhvua","efv","dkbuajxdc","zojdxfuafz","kzmucxvaw","effiexys","dgphksz","ivmvfgdxr","rlyxh","munhm","ydzpklyzq","weeribc","lfrjgqd","hysk","jwbiozcfjw","zuemgz","dpv","eyuenmwlml","pvrattsprf","zdzc","mrmuaqg","dmocgizzry","zjgebwem","enhoxn","zhktkkr","aiat","ctgdildo","vfge","tlm","mnjrqus","rnkxvyzczk","erzie","oqmvusuoh","hgoeqlsswj","jmraza","ihouxygs","sed","hnbumaf","vtmrjuuu","smmqcph","imbno","jikvilrcjq","sim","onduyulgka","ftbjlzqso","whlkfprb","rydnzwttg","bksu","esmjwnompo","ujeeieqpd","wdlkyh","mkjqirrgf","qkeuwqezed","klgsgcaiv","zjdp","fkqqeam","kazgzgb","kgu","wywfez","ibwkkkqbks","ugehd","cvsq","cawfibnjq","mhag","lbqae","ezsjwslhnq","uemvhjywvc","wsu","uxmmgezm","cbsxcdner","ytflqtjm","zwyctfl","rklgu","vjmgef","apavasb","qxna","afqclg","xtwg","sptgabydbo","bycnkqx","maahs","xkkzpowzmg","jnz","ffulzk","zzzrsitwkb","gauslarlsr","dmnx","qmyc","ptzqpgg","mgeochxjs","onsux","yprubwnxhu","pev","gtwl","vrui","wftckofux","zocqitifo","rehnwzxz","fpabntiq","ser","inntrxyl","ijc","jrsxk","evzv","fxpb","gusmecxtub","wqtfwr","hkk","oivrvhamtx","yyum","rfxxbfxi","ogb","gmhhnoga","xoacprd","qwzcxh","ghgksf","kknnsgh","sokjtrfs","res","tzbcvic","izewerm","rfemuuh","dunouhmig","uxn","cklibxvfam","dxhccr","mgbxi","unyx","cfoukxp","atkplv","yzxlb","gxubjcotwb","xiziixog","ohdup","ftyqnu","zrrlygv","snt","siqcuc","przpc","vrkorouo","qzgzj","ayxyiuuty","pqlvz","gldg","lpbwjcdsdk","uwrxnzteex","wkvbwvw","yrpjhaliux","afmi","lbzrv","ddmhunr","xxbwft","vump","koakhqw","eaenrrroz","ikcnmgtmq","cbumz","tenwkrnu","wgqiu","arvjzmwug","ccy","ugxzr","hqmkzndpu","peg","ieqnwcgu","yribvemhd","ycltkdmr","swltx","fnx","rqyjvj","gsji","zpophujc","cdtrzt","szh","nixsidti","rkej","gcudgfdj","eoplf","nbnfvvlhpm","husw","ljpxuisrrl","unig","vwefrqibpl","stfyjoh","thklbjdl","cpgexuwgp","zgqou","ndfj","lsqhbpa","mzdnyugghh","emr","tjvupnto","xtkjevr","wknihmyea","rbtyopgk","bpzkbho","zsmfbmjzpu","gjragqy","fqpvzrtcjs","wvlydfoh","qgyxili","ktv","ejtphm","fxbtxih","motshizlm","wrvqesg","pvjdi","xavmfpsc","pzm","zqorf","wcjxsz","bvc","lizuywxu","poqjyokhws","qhtoeaie","jufieyqsur","fokywwnb","kyew","pfmmq","ikqyyj","ibrasur","lmpasc","fgimsu","ctkeqtdf","dkzcrn","bna","zhjbcimci","lazns","gykcxg","sdf","tasglj","uuc","twnqwwkha","ftgmpluh","clwylyhhyk","ezojfbpis","dqexbec","ndpjpo","iolt","nkoolfic","htg","hpmmbejs","ptm","kqznlyxgal","vehwhdt","wtqtenifyy","fmsocnvuw","hygznhlsfh","jijsyy","ygcdjyhqvf","dzq","onvn","anwv","lre","wnygcqzjvt","mljjfihbv","krcukr","homttp","wirglzdesa","mbexs","roq","xxjzoxzfwo","kkv","iow","vfxihyv","wksohilt","gfhjz","uwf","mmftsde","huzkjyj","jvsyipcsg","vwzfb","pxppuu","uwqerv","kkcyjugst","wbkuuanzhb","xtauokub","cmhwr","reipo","kose","pyrzurub","zuclf","djek","hecrxic","hmjghoraa","wooyw","bntwa","tdgtfzm","nlxxtq","qtmn","cicyhbw","kwemtcoxgy","irehq","ysuo","uugwj","ocxz","hspiakzxf","ompzhp","eotmqvjvd","iazuzgpgum","vnjmrkui","yhbzmgzajm","gfkevbhz","xnj","ivtrz","bfaugfte","mby","pqrtef","xwtclm","kmi","ubykle","zsmtm","dqzpvdyhj","fepsbbpcpc","qygy","bmnokcgvw","hkzgslevku","slltheak","wbvlvfoqax","mehcvdffl","zgizr","anm","sby","zzxhw","pcsierk","kpwjglwdrv","ffqjrqktp","vkhvkzg","tbvxplvx","jqswcq","cepb","eoqbygkb","qyzczy","angkk","ogdk","hlog","naajnwcp","ona","wfyouylof"};
        assertThat(TextJustification.justifyText(w, m), is(soln));
    }

    // TODO: add more test cases here
}
