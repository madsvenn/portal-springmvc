import com.leozhang.portalssm.service.DeptService;
import com.leozhang.portalssm.service.impl.DeptServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class test_dept {

    private static Logger logger= LogManager.getLogger(LogManager.DEFAULT_CONFIGURATION_FILE);

    @Test
    public void selecttest(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        DeptServiceImpl service = context.getBean(DeptServiceImpl.class);
        service.deleteDeptById(23L);
        System.out.println(1111111);
    }

    @Test
    public void delete(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        int[] nums = {2,3,2};
        int []dp = new int[nums.length+1];
        dp[0]= 0;
        dp[1]= nums[0];
        int i =2;
        while (i<nums.length+1){
            dp[i] = Math.max(dp[i-1],dp[i-2]+nums[i-1]);
            i++;
        }

        for (int x:
             dp) {
            System.out.println(x);
        }
    }
}
