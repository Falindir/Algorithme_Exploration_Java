package tp.tools;

import tp.tools.Form2D.Point2D;
import tp.tools.Form2D.Triangle2D;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jimmy on 15/04/15.
 */
public class FormOfPoint {

    public static List<Point2D> getForm1() {

        List<Point2D>  points = new ArrayList<Point2D>();

        int[] sommet[] = {
            {416,529},{505,438},{268,188},{188,276},{312,595},{542,411},{331,195},{483,331},{427,216},{260,241},
            {137,456},{291,236},{412,291},{430,617},{192,342},{308,241},{460,265},{355,232},{537,326},{556,288},
            {251,264},{537,260},{195,443},{382,596},{404,224},{542,321},{166,516},{472,256},{417,177},{411,505},
            {404,166},{494,595},{502,353},{251,481},{558,467},{561,361},{480,561},{147,387},{404,248},{227,201},
            {241,532},{251,472},{360,249},{552,390},{171,267},{491,502},{329,520},{168,328},{451,232},{584,446},
            {345,540},{121,380},{256,307},{217,249},{435,560},{508,460},{128,464},{451,500},{363,227},{585,428},
            {192,404},{152,518},{489,536},{473,500},{275,294},{459,488},{540,414},{140,364},{140,380},{350,609},
            {528,433},{267,539},{422,521},{160,248},{464,518},{339,529},{472,513},{219,403},{427,288},{262,576},
            {344,604},{204,320},{441,204},{281,630},{516,276},{486,379},{220,508},{510,513},{465,540},{196,454},
            {260,254},{540,536},{230,280},{470,329},{313,576},{406,209},{488,425},{411,268},{488,420},{444,264},
            {168,491},{427,225},{312,606},{564,411},{160,438},{212,454},{531,249},{145,360},{459,310},{225,294},
            {243,539},{249,246},{556,342},{564,332},{433,494},{313,584},{483,547},{374,235},{505,257},{265,243},
            {432,624},{412,228},{278,294},{456,304},{212,286},{513,300},{545,380},{222,553},{208,398},{560,464},
            {401,192},{262,580},{350,568},{233,281},{331,198},{233,481},{220,564},{395,224},{328,182},{137,355},
            {512,342},{510,326},{236,248},{454,248},{459,545},{152,409},{425,174},{428,617},{524,468},{454,472},
            {116,379},{515,326},{352,537},{216,464},{436,179},{192,524},{424,612},{355,203},{476,366},{460,316},
            {158,545},{166,406},{486,587},{198,214},{188,355},{446,190},{248,515},{528,521},{332,561},{550,473},
            {227,609},{366,523},{204,262},{153,443},{371,544},{524,296},{459,614},{137,374},{465,356},{492,222},
            {288,614},{347,265},{486,518},{361,217},{518,446},{180,305},{315,172},{329,201},{241,614},{198,368},
            {556,316},{252,622},{212,264},{353,280},{204,267},{185,300},{515,342},{344,571},{155,513},{494,289},
        };



        int i = 0;
        for(int[] s : sommet) {
            Point2D point = new Point2D(s[0], s[1]);
           // point.setName("" + i);
            points.add(point);
            i++;
        }

        Collections.sort(points);

        int j = 0;
        for(Point2D point : points) {

           // point.setName(""+ j);
            j++;
        }
/*
        List<Point2D> pp = new ArrayList<Point2D>();
        for(int k = 0; k < 30; k++) {

            pp.add(points.get(k));
        }*/
        return points;
    }

    public static List<Triangle2D> getForm1Triangle() {

        List<Point2D> points = getForm1();

        List<Triangle2D> triangles = new ArrayList<Triangle2D>();

        triangles.add(new Triangle2D(points.get(0), points.get(1), points.get(2)));
        triangles.add(new Triangle2D(points.get(3), points.get(1), points.get(2)));
        triangles.add(new Triangle2D(points.get(0), points.get(1), points.get(4)));
        triangles.add(new Triangle2D(points.get(4), points.get(1), points.get(3)));
        triangles.add(new Triangle2D(points.get(4), points.get(5), points.get(3)));
        triangles.add(new Triangle2D(points.get(4), points.get(5), points.get(6)));
        triangles.add(new Triangle2D(points.get(4), points.get(6), points.get(3)));
        triangles.add(new Triangle2D(points.get(7), points.get(6), points.get(3)));
        triangles.add(new Triangle2D(points.get(4), points.get(6), points.get(8)));
        triangles.add(new Triangle2D(points.get(6), points.get(7), points.get(8)));
        triangles.add(new Triangle2D(points.get(3), points.get(7), points.get(8)));
        triangles.add(new Triangle2D(points.get(8), points.get(9), points.get(3)));
        triangles.add(new Triangle2D(points.get(2), points.get(3), points.get(10)));
        triangles.add(new Triangle2D(points.get(9), points.get(3), points.get(10)));
        triangles.add(new Triangle2D(points.get(9), points.get(10), points.get(11)));
        triangles.add(new Triangle2D(points.get(9), points.get(8), points.get(11)));
        triangles.add(new Triangle2D(points.get(12), points.get(10), points.get(11)));
        triangles.add(new Triangle2D(points.get(12), points.get(10), points.get(13)));
        triangles.add(new Triangle2D(points.get(14), points.get(10), points.get(13)));
        triangles.add(new Triangle2D(points.get(14), points.get(12), points.get(13)));
        triangles.add(new Triangle2D(points.get(0), points.get(4), points.get(15)));
        triangles.add(new Triangle2D(points.get(4), points.get(8), points.get(15)));
        triangles.add(new Triangle2D(points.get(8), points.get(9), points.get(15)));
        triangles.add(new Triangle2D(points.get(9), points.get(11), points.get(15)));
        triangles.add(new Triangle2D(points.get(11), points.get(12), points.get(15)));
        triangles.add(new Triangle2D(points.get(12), points.get(14), points.get(15)));
        triangles.add(new Triangle2D(points.get(15), points.get(14), points.get(16)));
        triangles.add(new Triangle2D(points.get(14), points.get(16), points.get(17)));
        triangles.add(new Triangle2D(points.get(15), points.get(16), points.get(17)));
        triangles.add(new Triangle2D(points.get(15), points.get(18), points.get(17)));
        triangles.add(new Triangle2D(points.get(15), points.get(18), points.get(19)));
        triangles.add(new Triangle2D(points.get(20), points.get(18), points.get(19)));
        triangles.add(new Triangle2D(points.get(20), points.get(18), points.get(17)));










        return triangles;
    }

    public static List<Point2D> getForm2() {

        List<Point2D> points = new ArrayList<Point2D>();

        int[] sommet[] = {
                {296, 218}, {504, 172}, {384, 740}, {374, 736}, {276, 568}, {168, 594}, {362, 172}, {216, 516}, {244, 564}, {346, 22},
                {394, 330}, {184, 598}, {266, 656}, {228, 316}, {200, 222}, {200, 136}, {370, 56}, {474, 632}, {286, 650}, {248, 252},
                {544, 668}, {360, 102}, {248, 216}, {346, 170}, {294, 650}, {482, 470}, {434, 202}, {418, 576}, {498, 596}, {236, 424},
                {312, 484}, {530, 580}, {402, 720}, {416, 96}, {338, 344}, {178, 452}, {418, 154}, {482, 650}, {530, 450}, {162, 552},
                {394, 746}, {230, 142}, {498, 124}, {490, 164}, {460, 572}, {488, 662}, {444, 74}, {470, 150}, {506, 544}, {204, 478},
                {182, 438}, {540, 446}, {354, 712}, {360, 624}, {254, 104}, {270, 590}, {428, 120}, {250, 456}, {404, 658}, {206, 528},
                {378, 176}, {198, 476}, {488, 322}, {420, 674}, {458, 590}, {494, 320}, {272, 670}, {184, 154}, {380, 612}, {536, 488},
                {230, 228}, {346, 74}, {382, 648}, {260, 460}, {406, 196}, {316, 732}, {230, 526}, {556, 514}, {564, 440}, {408, 304},
                {246, 494}, {426, 158}, {286, 692}, {230, 150}, {478, 724}, {336, 70}, {382, 634}, {218, 554}, {362, 652}, {466, 528},
                {474, 152}, {316, 616}, {508, 542}, {420, 266}, {314, 584}, {164, 594}, {292, 556}, {342, 592}, {522, 618}, {272, 700},
                {366, 690}, {498, 478}, {210, 472}, {234, 200}, {450, 620}, {288, 518}, {300, 598}, {206, 620}, {254, 96}, {296, 76},
                {238, 306}, {454, 112}, {442, 668}, {368, 676}, {396, 272}, {336, 706}, {282, 108}, {536, 696}, {450, 126}, {512, 172},
                {436, 48}, {432, 712}, {184, 170}, {218, 626}, {476, 180}, {182, 638}, {208, 118}, {286, 630}, {230, 644}, {468, 718},
                {166, 494}, {394, 676}, {576, 486}, {296, 534}, {178, 548}, {254, 94}, {362, 22}, {336, 650}, {400, 644}, {554, 594},
                {290, 620}, {200, 396}, {470, 146}, {208, 588}, {276, 532}, {254, 532}, {450, 138}, {302, 578}, {370, 46}, {422, 694},
                {384, 664}, {444, 310}, {288, 656}, {482, 132}, {510, 196}, {432, 578}, {302, 56}, {456, 622}, {508, 468}, {194, 148},
                {182, 544}, {400, 80}, {370, 426}, {220, 474}, {372, 676}, {350, 180}, {176, 572}, {288, 630}, {466, 566}, {412, 680},
                {238, 414}, {510, 454}, {428, 644}, {400, 158}, {536, 630}, {278, 116}, {378, 680}, {508, 710}, {170, 558}, {194, 562},
                {476, 192}, {282, 550}, {536, 516}, {478, 518}, {506, 556}, {380, 750}, {280, 90}, {298, 470}, {196, 514}, {176, 620},
                {420, 668}, {448, 752}, {424, 732}, {532, 710}, {246, 178}, {194, 236}, {376, 580}, {288, 728}, {336, 474}, {294, 454},
                {484, 674}, {312, 738}, {320, 750}, {198, 420}, {212, 234}, {430, 738}, {356, 612}, {450, 148}, {510, 174}, {220, 438},
                {294, 100}, {248, 114}, {252, 516}, {438, 658}, {356, 76}, {276, 648}, {322, 26}, {406, 70}, {322, 472}, {556, 478},
                {478, 478}, {406, 738}, {546, 694}, {278, 584}, {318, 736}, {482, 596}, {374, 688}, {340, 146}, {350, 70}, {304, 626},
                {424, 746}, {334, 340}, {170, 616}, {522, 546}, {230, 556}, {488, 494}, {508, 556}, {482, 542}, {552, 626}, {360, 696},
                {480, 610}, {348, 450}, {502, 576}, {416, 92}, {188, 146}, {258, 504}, {372, 724}, {282, 624}, {298, 564}, {284, 726},
                {492, 112}, {278, 698}, {532, 538}, {246, 184}, {498, 682}, {394, 310}, {442, 692}, {300, 554}, {226, 170}, {278, 64},
                {530, 686}, {462, 108}, {270, 74}, {498, 586}, {188, 414}, {298, 98}, {240, 112}, {262, 644}, {252, 132}, {386, 588},
                {226, 242}, {554, 630}, {434, 710}, {360, 178}, {542, 590}, {538, 476}, {216, 474}, {472, 144}, {498, 656}, {198, 616},
                {158, 568}, {248, 100}, {238, 394}, {476, 652}, {242, 458}, {550, 522}, {254, 476}, {462, 616}, {398, 652}, {284, 710},
                {402, 592}, {456, 124}, {362, 412}, {298, 42}, {460, 186}, {206, 634}, {476, 524}, {224, 318}, {264, 546}, {308, 86},
                {340, 186}, {370, 734}, {402, 742}, {460, 166}, {376, 56}, {476, 202}, {364, 412}, {352, 652}, {284, 660}, {282, 90},
                {462, 466}, {494, 710}, {296, 698}, {328, 644}, {206, 484}, {302, 718}, {490, 328}, {494, 154}, {444, 690}, {184, 612},
                {218, 444}, {208, 484}, {320, 676}, {202, 562}, {156, 590}, {300, 600}, {364, 624}, {536, 624}, {384, 706}, {474, 536},
                {464, 530}, {400, 54}, {174, 210}, {452, 626}, {478, 648}, {500, 572}, {222, 180}, {440, 168}, {480, 278}, {304, 690},
                {326, 196}, {176, 458}, {404, 588}, {204, 380}, {208, 216}, {202, 232}, {556, 644}, {198, 568}, {450, 562}, {238, 204},
                {440, 478}, {250, 448}, {324, 478}, {488, 196}, {514, 712}, {388, 476}, {276, 120}, {528, 620}, {172, 200}, {350, 760},
                {266, 606}, {268, 466}, {416, 94}, {210, 442}, {400, 294}, {326, 642}, {310, 168}, {354, 148}, {268, 508}, {314, 580},
                {456, 748}, {294, 606}, {240, 592}, {314, 586}, {200, 506}, {548, 572}, {240, 404}, {494, 530}, {208, 188}, {358, 132},
                {536, 524}, {168, 588}, {234, 200}, {410, 592}, {314, 732}, {446, 214}, {440, 592}, {216, 592}, {418, 582}, {402, 304},
                {434, 616}, {588, 478}, {216, 646}, {466, 154}, {346, 750}, {290, 622}, {304, 684}, {230, 564}, {420, 36}, {370, 44},
                {516, 600}, {294, 650}, {342, 712}, {238, 558}, {370, 484}, {280, 676}, {344, 44}, {494, 696}, {458, 708}, {350, 724},
                {412, 84}, {478, 648}, {510, 658}, {468, 602}, {414, 670}, {322, 608}, {492, 586}, {236, 564}, {162, 500}, {476, 262},
                {366, 726}, {270, 660}, {470, 204}, {168, 516}, {478, 442}, {238, 440}, {454, 162}, {228, 218}, {376, 626}, {290, 586},
                {320, 154}, {174, 518}, {382, 690}, {430, 646}, {438, 300}, {460, 152}, {216, 388}, {270, 134}, {256, 202}, {176, 548},
                {290, 604}, {254, 146}, {546, 574}, {530, 434}, {296, 618}, {426, 580}, {462, 566}, {422, 726}, {274, 76}, {290, 676},
                {234, 462}, {480, 628}, {244, 506}, {542, 474}, {364, 412}, {510, 598}, {344, 162}, {434, 298}, {172, 632}, {462, 666},
                {430, 726}, {230, 532}, {238, 564}, {298, 74}, {382, 764}, {368, 608}, {548, 538}, {494, 686}, {402, 68}, {232, 420},
                {410, 610}, {534, 618}, {238, 396}, {340, 176}, {214, 418}, {476, 528}, {190, 608}, {226, 168}, {208, 222}, {432, 42},
                {466, 484}, {308, 52}, {178, 222}, {276, 504}, {214, 514}, {390, 154}, {420, 106}, {376, 312}, {484, 552}, {462, 124},
                {562, 636}, {440, 124}, {346, 642}, {334, 40}, {506, 396}, {536, 432}, {292, 180}, {446, 694}, {418, 698}, {190, 438},
                {178, 640}, {210, 142}, {228, 426}, {482, 168}, {262, 600}, {488, 716}, {486, 710}, {430, 128}, {448, 670}, {334, 198},
                {566, 510}, {334, 606}, {336, 88}, {496, 524}, {318, 424}, {370, 584}, {192, 528}, {180, 522}, {216, 522}, {580, 484},
                {386, 300}, {542, 430}, {194, 564}, {202, 448}, {376, 738}, {346, 626}, {434, 672}, {212, 554}, {418, 48}, {516, 558},
                {254, 148}, {432, 618}, {552, 590}, {288, 710}, {330, 198}, {480, 206}, {480, 734}, {430, 156}, {436, 294}, {344, 756},
                {396, 290}, {210, 596}, {228, 232}, {538, 432}, {486, 432}, {510, 706}, {436, 56}, {260, 76}, {530, 514}, {382, 700},
                {582, 488}, {512, 528}, {366, 402}, {186, 586}, {214, 194}, {236, 632}, {274, 594}, {356, 740}, {436, 112}, {292, 714},
                {424, 286}, {436, 732}, {218, 184}, {372, 754}, {520, 676}, {250, 450}, {408, 330}, {182, 156}, {460, 158}, {306, 214},
                {182, 496}, {360, 72}, {424, 660}, {484, 572}, {198, 200}, {438, 608}, {166, 212}, {458, 736}, {238, 572}, {522, 670},
                {380, 298}, {386, 274}, {334, 62}, {560, 504}, {474, 678}, {356, 630}, {488, 158}, {218, 558}, {362, 122}, {436, 300},
                {226, 162}, {344, 586}, {468, 722}, {310, 708}, {306, 556}, {550, 542}, {522, 614}, {234, 568}, {378, 312}, {402, 40},
                {348, 180}, {522, 566}, {354, 644}, {210, 518}, {482, 560}, {354, 616}, {354, 122}, {444, 310}, {434, 50}, {582, 494},
                {468, 682}, {176, 458}, {204, 610}, {296, 540}, {260, 514}, {476, 520}, {440, 50}, {498, 680}, {386, 142}, {488, 480},
                {560, 504}, {314, 606}, {480, 576}, {186, 460}, {444, 564}, {314, 558}, {232, 598}, {410, 146}, {542, 432}, {450, 714},
                {294, 468}, {404, 294}, {472, 176}, {404, 324}, {460, 140}, {362, 36}, {186, 562}, {220, 648}, {214, 562}, {224, 116},
                {316, 484}, {516, 570}, {524, 644}, {356, 188}, {468, 604}, {224, 634}, {242, 90}, {392, 600}, {312, 626}, {346, 488},
                {440, 622}, {542, 468}, {472, 438}, {398, 622}, {204, 144}, {364, 614}, {488, 168}, {416, 756}, {356, 188}, {352, 116},
                {432, 678}, {450, 594}, {180, 622}, {220, 490}, {490, 410}, {426, 610}, {312, 78}, {206, 568}, {484, 436}, {186, 494},
                {284, 94}, {450, 670}, {496, 620}, {520, 694}, {462, 626}, {218, 536}, {364, 92}, {272, 472}, {554, 652}, {182, 214},
                {344, 88}, {340, 644}, {476, 732}, {242, 160}, {336, 746}, {214, 612}, {234, 578}, {484, 488}, {342, 496}, {366, 612},
                {264, 188}, {466, 626}, {234, 150}, {276, 64}, {232, 560}, {480, 106}, {506, 512}, {232, 404}, {458, 400}, {250, 546},
                {390, 702}, {336, 66}, {200, 540}, {468, 96}, {402, 94}, {218, 442}, {426, 670}, {422, 98}, {316, 200}, {464, 156},
                {190, 546}, {224, 498}, {470, 594}, {440, 572}, {264, 188}, {466, 144}, {372, 32}, {476, 142}, {220, 642}, {240, 132},
                {530, 538}, {302, 660}, {482, 674}, {254, 500}, {370, 66}, {554, 618}, {502, 696}, {202, 608}, {394, 290}, {362, 164},
                {366, 696}, {358, 650}, {254, 488}, {216, 168}, {228, 250}, {268, 668}, {480, 566}, {338, 696}, {192, 610}, {384, 632},
                {216, 446}, {536, 578}, {292, 594}, {286, 554}, {342, 652}, {404, 196}, {416, 686}, {522, 560}, {458, 60}, {448, 532},
                {472, 182}, {194, 610}, {468, 438}, {242, 426}, {214, 584}, {188, 162}, {164, 192}, {434, 654}, {430, 730}, {458, 86},
                {428, 696}, {176, 554}, {370, 716}, {412, 108}, {324, 330}, {442, 676}, {204, 550}, {426, 742}, {308, 704}, {238, 434},
                {206, 226}, {390, 702}, {584, 474}, {484, 726}, {496, 358}, {320, 650}, {210, 248}, {554, 498}, {312, 728}, {462, 634},
                {456, 598}, {314, 706}, {220, 194}, {228, 226}, {340, 436}, {224, 556}, {236, 526}, {542, 500}, {358, 686}, {238, 422},
                {216, 386}, {394, 654}, {390, 270}, {170, 562}, {452, 464}, {340, 670}, {166, 602}, {326, 142}, {520, 498}, {258, 188},
        };

        for(int[] s : sommet) {
            Point2D point = new Point2D(s[0], s[1]);
            points.add(point);
        }

        Collections.sort(points);

        List<Point2D> pp = new ArrayList<Point2D>();

        for(int i = 0; i < points.size() - 1; i++) {

            if(points.get(i).getX() == points.get(i+1).getX() && points.get(i).getY() == points.get(i+1).getY() ) {
                pp.add(points.get(i));
            }
        }

        for(Point2D i : pp) {
            points.remove(i);
        }

        Collections.sort(points);

        return points;
    }

    public static List<Point2D> getForm3() {

        List<Point2D> points = new ArrayList<Point2D>();

        int[] sommet[] = {
                {417, 688}, {163, 321}, {425, 462}, {411, 691}, {196, 552}, {334, 433}, {200, 334}, {312, 392}, {552, 624}, {262, 537},
                {328, 566}, {416, 520}, {163, 452}, {401, 475}, {432, 604}, {278, 598}, {163, 444}, {59, 353}, {158, 326}, {360, 408},
                {321, 462}, {272, 606}, {115, 668}, {243, 462}, {297, 515}, {304, 670}, {78, 353}, {270, 568}, {323, 536}, {419, 688},
                {526, 612}, {457, 524}, {190, 424}, {59, 387}, {384, 428}, {156, 667}, {408, 550}, {176, 368}, {302, 571}, {198, 484},
                {128, 396}, {168, 494}, {220, 427}, {347, 608}, {395, 646}, {408, 600}, {326, 672}, {235, 636}, {204, 496}, {136, 710},
                {481, 680}, {166, 657}, {134, 377}, {211, 694}, {488, 699}, {172, 526}, {497, 632}, {283, 428}, {107, 681}, {454, 590},
                {531, 632}, {433, 548}, {187, 648}, {555, 641}, {361, 598}, {353, 478}, {320, 544}, {268, 547}, {456, 707}, {409, 584},
                {352, 566}, {49, 457}, {148, 478}, {49, 465}, {504, 712}, {172, 713}, {233, 321}, {267, 427}, {499, 593}, {478, 712},
                {233, 456}, {150, 710}, {262, 600}, {329, 627}, {118, 708}, {356, 472}, {145, 715}, {281, 665}, {158, 499}, {238, 572},
                {496, 646}, {54, 478}, {155, 664}, {243, 641}, {116, 396}, {518, 622}, {104, 488}, {148, 718}, {163, 323}, {556, 696},
                {96, 377}, {401, 508}, {240, 400}, {528, 676}, {179, 364}, {308, 480}, {44, 382}, {504, 590}, {115, 694}, {331, 392},
                {123, 404}, {414, 584}, {409, 665}, {212, 664}, {307, 523}, {356, 635}, {328, 507}, {281, 387}, {331, 675}, {334, 526},
                {177, 312}, {257, 489}, {353, 566}, {560, 662}, {84, 464}, {233, 441}, {315, 464}, {504, 716}, {368, 606}, {128, 529},
                {344, 451}, {486, 710}, {432, 552}, {422, 633}, {368, 654}, {163, 347}, {292, 404}, {246, 308}, {468, 694}, {403, 595},
                {334, 534}, {313, 480}, {582, 664}, {372, 646}, {430, 636}, {80, 390}, {177, 500}, {180, 680}, {316, 683}, {456, 718},
                {148, 484}, {206, 451}, {308, 553}, {56, 468}, {230, 520}, {235, 632}, {275, 600}, {206, 520}, {204, 462}, {288, 380},
                {467, 683}, {273, 462}, {430, 491}, {211, 681}, {278, 571}, {441, 667}, {219, 475}, {177, 534}, {153, 515}, {504, 593},
                {323, 592}, {236, 652}, {452, 510}, {62, 476}, {324, 550}, {161, 720}, {281, 435}, {353, 633}, {243, 644}, {140, 448},
                {260, 619}, {230, 612}, {104, 704}, {563, 718}, {28, 457}, {185, 691}, {268, 553}, {164, 460}, {566, 715}, {219, 395},
                {475, 545}, {46, 366}, {113, 369}, {401, 659}, {372, 675}, {467, 600}, {321, 532}, {318, 529}, {244, 508}, {193, 699},
                {366, 489}, {124, 692}, {276, 659}, {308, 592}, {459, 537}, {291, 496}, {352, 521}, {480, 731}, {563, 667}, {206, 454},
                {236, 366}, {414, 584}, {257, 476}, {270, 649}, {260, 662}, {550, 665}, {185, 675}, {192, 422}, {272, 596}, {174, 433},
                {278, 430}, {288, 657}, {324, 676}, {276, 606}, {222, 665}, {246, 641}, {390, 492}, {353, 620}, {276, 457}, {131, 411},
                {385, 424}, {406, 558}, {267, 627}, {417, 449}, {592, 651}, {416, 646}, {222, 508}, {248, 313}, {520, 649}, {217, 433},
                {174, 550}, {320, 614}, {318, 577}, {403, 672}, {267, 376}, {268, 409}, {65, 499}, {270, 488}, {280, 476}, {288, 681},
                {300, 441}, {518, 710}, {193, 675}, {324, 526}, {214, 390}, {318, 436}, {355, 632}, {472, 510}, {238, 379}, {275, 675},
                {153, 345}, {211, 457}, {307, 364}, {153, 428}, {212, 465}, {392, 513}, {315, 449}, {401, 590}, {78, 374}, {416, 678},
                {131, 668}, {508, 619}, {220, 494}, {436, 566}, {246, 609}, {249, 659}, {211, 672}, {288, 561}, {246, 502}, {246, 585},
                {73, 488}, {312, 675}, {411, 672}, {195, 688}, {299, 380}, {81, 376}, {185, 507}, {313, 507}, {265, 598}, {211, 419},
                {332, 470}, {451, 651}, {214, 659}, {262, 577}, {473, 595}, {163, 321}, {137, 668}, {217, 539}, {75, 388}, {313, 641},
                {107, 704}, {436, 681}, {280, 576}, {382, 510}, {420, 585}, {441, 568}, {305, 556}, {64, 348}, {192, 449}, {300, 395},
                {267, 318}, {281, 536}, {174, 358}, {304, 387}, {334, 681}, {371, 601}, {398, 672}, {225, 492}, {299, 406}, {401, 582},
                {315, 539}, {260, 486}, {204, 457}, {28, 449}, {390, 630}, {188, 649}, {132, 488}, {526, 667}, {350, 424}, {468, 689},
                {363, 531}, {552, 726}, {244, 652}, {443, 681}, {404, 641}, {305, 580}, {187, 340}, {352, 577}, {446, 513}, {145, 393},
                {48, 358}, {323, 531}, {89, 497}, {227, 390}, {236, 467}, {224, 660}, {336, 552}, {489, 627}, {425, 499}, {385, 568},
                {217, 664}, {217, 686}, {396, 604}, {280, 481}, {156, 412}, {529, 622}, {262, 372}, {329, 641}, {299, 524}, {150, 488},
                {561, 710}, {444, 536}, {115, 699}, {233, 672}, {302, 678}, {472, 731}, {227, 481}, {355, 456}, {390, 644}, {243, 686},
                {484, 553}, {46, 452}, {387, 646}, {291, 446}, {182, 430}, {51, 377}, {406, 689}, {80, 464}, {225, 675}, {20, 481},
                {430, 500}, {204, 339}, {161, 334}, {244, 660}, {288, 454}, {340, 556}, {172, 656}, {387, 656}, {476, 624}, {139, 401},
                {448, 520}, {86, 513}, {200, 329}, {435, 662}, {92, 398}, {227, 401}, {57, 393}, {526, 680}, {390, 534}, {334, 571},
                {513, 627}, {177, 715}, {72, 342}, {192, 683}, {457, 571}, {68, 352}, {192, 555}, {446, 705}, {163, 408}, {393, 512},
                {540, 716}, {264, 660}, {262, 534}, {209, 468}, {424, 569}, {486, 564}, {542, 670}, {152, 480}, {284, 489}, {312, 614},
                {396, 507}, {105, 369}, {129, 489}, {422, 689}, {248, 462}, {228, 484}, {438, 561}, {43, 467}, {310, 512}, {67, 464},
                {91, 510}, {232, 465}, {201, 686}, {241, 630}, {270, 409}, {264, 648}, {486, 633}, {241, 612}, {436, 593}, {264, 552},
                {312, 364}, {499, 668}, {267, 598}, {377, 544}, {380, 630}, {280, 372}, {427, 523}, {57, 460}, {187, 555}, {248, 403},
                {288, 348}, {571, 657}, {244, 665}, {353, 572}, {388, 667}, {121, 396}, {417, 536}, {347, 515}, {190, 440}, {192, 478},
                {299, 611}, {448, 587}, {348, 548}, {547, 737}, {187, 473}, {275, 502}, {174, 504}, {236, 368}, {304, 569}, {78, 358},
                {139, 681}, {171, 518}, {238, 529}, {235, 424}, {454, 699}, {152, 448}, {131, 395}, {280, 577}, {379, 662}, {195, 440},
                {81, 454}, {318, 380}, {206, 388}, {550, 643}, {307, 452}, {238, 518}, {251, 465}, {230, 433}, {547, 624}, {284, 622},
                {100, 468}, {347, 419}, {243, 587}, {510, 620}, {252, 566}, {329, 436}, {132, 403}, {268, 382}, {513, 603}, {340, 556},
                {297, 460}, {206, 385}, {385, 622}, {516, 708}, {430, 548}, {265, 403}, {222, 364}, {409, 628}, {184, 451}, {270, 524},
                {129, 409}, {209, 416}, {222, 456}, {491, 724}, {374, 433}, {372, 531}, {59, 392}, {267, 552}, {280, 576}, {211, 404},
                {414, 582}, {366, 500}, {96, 411}, {96, 494}, {241, 409}, {491, 737}, {233, 491}, {345, 590}, {585, 638}, {403, 483},
                {257, 600}, {179, 342}, {388, 545}, {428, 576}, {241, 344}, {337, 473}, {275, 412}, {278, 398}, {353, 572}, {211, 472},
                {312, 665}, {427, 534}, {464, 497}, {360, 654}, {452, 598}, {318, 368}, {193, 379}, {192, 662}, {233, 401}, {150, 484},
                {102, 515}, {160, 438}, {446, 492}, {489, 630}, {396, 662}, {369, 579}, {52, 481}, {385, 624}, {355, 528}, {145, 513},
                {307, 449}, {241, 686}, {404, 497}, {238, 512}, {555, 708}, {475, 640}, {296, 388}, {265, 569}, {384, 547}, {302, 558},
                {280, 371}, {289, 622}, {280, 667}, {529, 726}, {510, 641}, {376, 627}, {356, 548}, {302, 646}, {585, 660}, {588, 648},
                {227, 497}, {371, 587}, {260, 643}, {417, 545}, {422, 545}, {372, 536}, {67, 459}, {233, 617}, {84, 380}, {331, 409},
                {212, 651}, {284, 491}, {161, 353}, {348, 508}, {102, 692}, {425, 680}, {48, 454}, {460, 694}, {459, 713}, {166, 707},
                {384, 564}, {353, 598}, {252, 491}, {232, 440}, {340, 401}, {171, 406}, {216, 353}, {219, 635}, {203, 649}, {182, 542},
                {48, 486}, {332, 651}, {406, 467}, {398, 588}, {259, 366}, {454, 632}, {497, 635}, {224, 497}, {19, 465}, {129, 672},
                {204, 452}, {329, 516}, {460, 579}, {228, 420}, {457, 619}, {196, 689}, {267, 344}, {451, 521}, {96, 387}, {252, 630},
                {204, 467}, {318, 566}, {304, 564}, {187, 427}, {267, 576}, {48, 464}, {358, 638}, {432, 577}, {187, 500}, {102, 372},
                {203, 462}, {262, 473}, {406, 574}, {275, 593}, {348, 636}, {57, 465}, {272, 401}, {363, 507}, {438, 569}, {484, 742},
                {78, 500}, {454, 670}, {344, 612}, {304, 480}, {289, 416}, {129, 473}, {260, 502}, {268, 350}, {523, 726}, {230, 412},
                {476, 625}, {230, 611}, {382, 494}, {187, 339}, {214, 584}, {456, 713}, {83, 400}, {312, 483}, {140, 699}, {214, 560},
                {412, 628}, {164, 489}, {262, 632}, {188, 540}, {200, 574}, {446, 508}, {185, 379}, {204, 686}, {67, 371}, {177, 412},
                {508, 707}, {508, 625}, {265, 446}, {220, 403}, {368, 633}, {392, 449}, {192, 336}, {563, 718}, {228, 528}, {180, 441},
                {435, 476}, {419, 688}, {270, 555}, {83, 452}, {416, 452}, {126, 492}, {217, 372}, {268, 675}, {369, 516}, {238, 604},
                {212, 539}, {315, 644}, {443, 628}, {278, 561}, {409, 656}, {563, 700}, {323, 419}, {515, 656}, {451, 571}, {408, 476},
                {460, 496}, {571, 668}, {150, 668}, {233, 393}, {350, 512}, {193, 328}, {507, 740}, {316, 576}, {256, 534}, {440, 563},
                {547, 612}, {224, 416}, {523, 659}, {257, 470}, {376, 489}, {444, 540}, {204, 440}, {251, 619}, {105, 393}, {164, 536},
                {257, 571}, {296, 400}, {16, 465}, {262, 579}, {152, 681}, {201, 459}, {331, 494}, {35, 444}, {313, 369}, {328, 601},
                {312, 449}, {364, 640}, {152, 715}, {355, 484}, {385, 468}, {328, 667}, {148, 332}, {32, 486}, {326, 531}, {364, 443},
                {291, 513}, {128, 500}, {336, 483}, {512, 612}, {308, 545}, {40, 460}, {232, 684}, {371, 462}, {310, 630}, {76, 398},
                {209, 401}, {228, 432}, {483, 571}, {257, 652}, {529, 680}, {395, 576}, {496, 688}, {252, 632}, {444, 689}, {211, 515},
                {449, 696}, {286, 614}, {457, 499}, {233, 315}, {200, 656}, {395, 539}, {366, 425}, {356, 512}, {428, 643}, {451, 547},
                {584, 696}, {534, 641}, {542, 739}, {264, 592}, {360, 590}, {52, 347}, {131, 657}, {310, 540}, {363, 656}, {406, 454},
                {209, 384}, {280, 590}, {404, 520}, {548, 729}, {208, 398}, {534, 648}, {452, 585}, {356, 454}, {259, 486}, {366, 510},
        };

        for(int[] s : sommet) {
            Point2D point = new Point2D(s[0], s[1]);
            points.add(point);
        }

        return points;


    }


}
