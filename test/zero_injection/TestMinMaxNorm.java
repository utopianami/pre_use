package zero_injection;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import util.ClassifierPreUse;
import util.ReadFile;
import util.ReadFilebyItem;

public class TestMinMaxNorm {

	@Test
	public void test2(){
		
		ClassifierPreUse cp = new ClassifierPreUse();
		
		System.out.println(cp.classifier(-1.6));
		System.out.println(cp.classifier(-1.45));
		System.out.println(cp.classifier(-1.36));
		System.out.println(cp.classifier(-1.26));
		System.out.println(cp.classifier(-1.16));
		System.out.println(cp.classifier(-1.06));
		System.out.println(cp.classifier(-0.96));
		System.out.println(cp.classifier(-0.86));
		System.out.println(cp.classifier(-0.76));
		System.out.println(cp.classifier(-0.66));
		System.out.println(cp.classifier(-0.56));
		System.out.println(cp.classifier(-0.46));
		System.out.println(cp.classifier(-0.36));
		System.out.println(cp.classifier(-0.26));
		System.out.println(cp.classifier(-0.16));
		System.out.println(cp.classifier(-0.06));
		System.out.println(cp.classifier(0.06));
		System.out.println(cp.classifier(0.16));
		System.out.println(cp.classifier(0.26));
		System.out.println(cp.classifier(0.36));
		System.out.println(cp.classifier(0.46));
		System.out.println(cp.classifier(0.56));
		System.out.println(cp.classifier(0.66));
		System.out.println(cp.classifier(0.76));
		System.out.println(cp.classifier(0.86));
		System.out.println(cp.classifier(0.96));
		System.out.println(cp.classifier(1.06));
		System.out.println(cp.classifier(1.16));
		System.out.println(cp.classifier(1.26));
		System.out.println(cp.classifier(1.36));
		System.out.println(cp.classifier(1.46));
		System.out.println(cp.classifier(1.56));
		

			
		
	}

}
