import Files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
    public static void main(String[] args) {

        JsonPath js = new JsonPath(Payload.CoursePrice());
        //Print No of courses returned by API
        int count = js.getInt("courses.size()");
        System.out.println(count);

        //Print Purchase Amount
        int totalAmount=js.getInt("dashboard.purchaseAmount");
        System.out.println(totalAmount);

        //Print Title of the first course
        String titleOfFirstCourse=js.getString("courses[2].title");
        System.out.println(titleOfFirstCourse);

        //Print All course titles and their respective Prices
        //We handled it dynamically if anyone adds more no of courses then our code will not be affected.
        for(int i=0;i<count;i++){
            String CourseTitles=js.get("courses["+i+"].title");
//            int AllPrices=js.getInt("courses["+i+"].price");
            String AllPrices=js.get("courses["+i+"].price").toString();//Convert int into String
            System.out.println(CourseTitles);
            System.out.println(AllPrices);
        }
        //Print no of copies sold by RPA Course
        //First,You have to scan all the courses and if the title is equal to required title then print sold copies
        for(int i=0;i<count;i++){
            String CourseTitles=js.get("courses["+i+"].title");
            if(CourseTitles.equalsIgnoreCase("RPA")){
                String CopySoldByRPA=js.get("courses["+i+"].copies").toString();
                System.out.println(CopySoldByRPA);
                break;
            }
        }
        //Verify if Sum of all Course prices matches with Purchase Amount
    }
}
