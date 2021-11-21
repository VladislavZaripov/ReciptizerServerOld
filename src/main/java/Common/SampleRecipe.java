package Common;

import Common.Recipe.Recipe;
import Common.Recipe.Table1;
import Common.Recipe.Table2Row;
import Common.Recipe.Table3Row;
import java.util.ArrayList;
import java.util.List;

public class SampleRecipe {

    public Recipe createRecipe1 (){
        Table1 table1 = new Table1(1,"Салат с тунцом","Салаты","Европейская","Рыбные блюда",15,2,null,null);

        List<Table2Row> rowsTable2 = new ArrayList<>();
        rowsTable2.add(new Table2Row(1,1,"Консервированный тунец",1,"банка"));
        rowsTable2.add(new Table2Row(2,1, "Салат айсберг",1,"шт"));
        rowsTable2.add(new Table2Row(3,1, "Помидоры черри",10,"шт"));
        rowsTable2.add(new Table2Row(4,1, "Перепелиные яйца",6,"шт"));
        rowsTable2.add(new Table2Row(5,1, "Оливки",10,"шт"));
        rowsTable2.add(new Table2Row(6,1, "Каперсы",10,"шт"));
        rowsTable2.add(new Table2Row(7,1, "Соус Песто",1,"по вк"));
        rowsTable2.add(new Table2Row(8,1, "Оливковое масло",2,"ст.л"));
        rowsTable2.add(new Table2Row(9,1, "Дижонская горчица",1,"ч.л"));
        rowsTable2.add(new Table2Row(10,1, "Мёд",1,"ч.л"));

        List <Table3Row> rowsTable3 = new ArrayList<>();
        rowsTable3.add(new Table3Row(1,1,1,"Поставьте варить перепелиные яйца в холодную воду, после закипания варите 5 минут.",null,null));
        rowsTable3.add(new Table3Row(2,1,2,"После варки остудите яйца в холодной воде, почистите и порежьте пополам.",null,null));
        rowsTable3.add(new Table3Row(3,1,3,"Нарвите листья салата Айсберг и сложите по центру тарелки горкой, сверху выложите половину банки тунца.",null,null));
        rowsTable3.add(new Table3Row(4,1,4,"Украсьте салат яйцами, половинками черри, оливками и каперсами.",null,null));
        rowsTable3.add(new Table3Row(5,1,5,"Выложите соус Песто сверху на тунец.",null,null));
        rowsTable3.add(new Table3Row(6,1,6,"Смешайте оливковое масло, горчицу и мед и полейте полученным соусом овощи.",null,null));

        return new Recipe(table1, rowsTable2, rowsTable3);
    }

    public Recipe createRecipe2 (){
        Table1 table1 = new Table1(2,"Чиабатта с оливками и вялеными томатами","Выпечка","Европейская","Все",60,6,null,null);

        List<Table2Row> rowsTable2 = new ArrayList<>();
        rowsTable2.add(new Table2Row(11,2,"Мука",4,"ст"));
        rowsTable2.add(new Table2Row(12,2, "Соль",2,"ч.л"));
        rowsTable2.add(new Table2Row(13,2, "Сахар",2,"ч.л"));
        rowsTable2.add(new Table2Row(14,2, "Вода",2,"ст"));
        rowsTable2.add(new Table2Row(15,2, "Сухие дрожжи",1,"ч.л"));
        rowsTable2.add(new Table2Row(16,2, "Оливки",1,"по вк"));
        rowsTable2.add(new Table2Row(17,2, "Вяленые томаты",1,"по вк"));

        List <Table3Row> rowsTable3 = new ArrayList<>();
        rowsTable3.add(new Table3Row(7,2,1,"Смешиваем сухие ингредиенты, добавляем воду, замешиваем тесто.",null,null));
        rowsTable3.add(new Table3Row(8,2,2,"Полученую опару ставим в теплое место на 12-18 часов.",null,null));
        rowsTable3.add(new Table3Row(9,2,3,"Тесто выкладываем в форму и смешиваем с нарезанными кубиками оливки и томаты, даем тесту постоять в форме еще 2 часа.",null,null));
        rowsTable3.add(new Table3Row(10,2,4,"Духовку разогреваем до 220 градусов, перед тем как поставить тесто в печь снижаем градус до 200.",null,null));
        rowsTable3.add(new Table3Row(11,2,5,"Выпекаем 45 - 60 минут, в зависимости от вашей духовки.",null,null));
        rowsTable3.add(new Table3Row(12,2,6,"Дайте хлебу постоять под полотенцем минут 15, тогда корочка будет хрустящей, но не грубой, после храните хлеб в пакете, чтобы избежать зачерствления.",null,null));

        return new Recipe(table1, rowsTable2, rowsTable3);
    }

    public Recipe createRecipe3 (){
        Table1 table1 = new Table1(3,"Пирог со шпинатом и фетой","Выпечка","Европейская","Все",35,4,null,null);

        List<Table2Row> rowsTable2 = new ArrayList<>();
        rowsTable2.add(new Table2Row(18,3, "Кедровые орешки",100,"гр"));
        rowsTable2.add(new Table2Row(19,3, "Яйца",5,"шт"));
        rowsTable2.add(new Table2Row(20,3, "Фета",300,"гр"));
        rowsTable2.add(new Table2Row(21,3, "Чеддер",50,"гр"));
        rowsTable2.add(new Table2Row(22,3,  "Молодой шпинат",400,"гр"));
        rowsTable2.add(new Table2Row(23,3, "Тесто Фило",270,"гр"));
        rowsTable2.add(new Table2Row(24,3,  "Орегано",1,"щеп"));
        rowsTable2.add(new Table2Row(25,3, "Цедра лимона",1,"шт"));
        rowsTable2.add(new Table2Row(26,3, "Оливковое масло",1,"по вк"));
        rowsTable2.add(new Table2Row(27,3,  "Сливочное масло",1,"по вк"));

        List <Table3Row> rowsTable3 = new ArrayList<>();
        rowsTable3.add(new Table3Row(13,3,1,"Обжарьте кедровые орешки.",null,null));
        rowsTable3.add(new Table3Row(14,3,2,"Разбейте в миску яйца и раскрошите фету, натрите чеддер. Добавьте туда щепотку орегано, цедру лимона и немного оливкового масла. Когда орехи подрумянятся, добавьте их в яичную смесь и все тщательно перемешайте.",null,null));
        rowsTable3.add(new Table3Row(15,3,3,"Поставьте сковороду на огонь, налейте в нее немного оливкового масла и положите кусочек сливочного, выложите шпинат. Обжаривайте помешивая.",null,null));
        rowsTable3.add(new Table3Row(16,3,4,"Застелите рабочую поверхность листом бумаги для выпечки, длиной примерно 50 см. Слегка смажьте бумагу оливковым маслом, сожмите ее и снова разгладьте. Выложите на бумагу 4 листа теста фило большим прямоугольником, располагая их в нахлест, чтобы они почти полностью покрыли лист бумаги. Смажьте тесто оливковым маслом, посыпьте щепоткой соли. Повторяйте пока не получится тройной слой листов фило.",null,null));
        rowsTable3.add(new Table3Row(17,3,5,"Когда шпинат полностью привянет, добавьте его в яичную смесь. Аккуратно перенесите бумагу со слоем теста в форму, оставив края свисать, прижмите к углу между дном и бортами, влейте яичную смесь. Сложите тесто поверх начинки.",null,null));
        rowsTable3.add(new Table3Row(18,3,6,"Поставьте пирог в духовку, разогретую до 200 грудусов на 18-20 минут, пока пирог не зарумянится и не станет хрустящим.",null,null));

        return new Recipe(table1, rowsTable2, rowsTable3);
    }

    public Recipe createRecipe4 (){
        Table1 table1 = new Table1(4,"Фахитас с курицей","Основные блюда","Латиноамериканская","Все",30,6,null,null);

        List<Table2Row> rowsTable2 = new ArrayList<>();
        rowsTable2.add(new Table2Row(28,4, "Куриная грудка",800,"гр"));
        rowsTable2.add(new Table2Row(29,4, "Сладкий перец",3,"шт"));
        rowsTable2.add(new Table2Row(30,4, "Красный лук",1,"шт"));
        rowsTable2.add(new Table2Row(31,4, "Мексиканская лепешка",1,"уп"));
        rowsTable2.add(new Table2Row(32,4, "Сметана",1,"по вк"));
        rowsTable2.add(new Table2Row(33,4, "Соус Гуакомоле",1,"по вк"));
        rowsTable2.add(new Table2Row(34,4, "Приправа Фахитас",1,"шт"));

        List <Table3Row> rowsTable3 = new ArrayList<>();
        rowsTable3.add(new Table3Row(19,4,1,"Нарежьте курицу тонкими полосками, также порежьте полосками овощи.",null,null));
        rowsTable3.add(new Table3Row(20,4,2,"Обжарьте на сковороде до полной готовности, в конце добавьте приправу \"Фахитас\".",null,null));
        rowsTable3.add(new Table3Row(21,4,3,"Положите несоклько ложек курицы с овощами на лепешку, сверху добавьте ложку сметаны и соус гуакомоле, заверните в конверт.",null,null));

        return new Recipe(table1, rowsTable2, rowsTable3);
    }
}