Change the path of the testate sheet in the file "TestUtil.java"
Change the path of the config file in the place "TestBase.java"
Check for the chrome driver path in. "TestBase.java"


To run the test case 
src>main>resources -> testng.xml


I have used Thread.sleep in few places, due to time constraint.  I did not have enought time to replace that with explicit wait, I know this is a deprecated method. 
As the page load takes different time durring the execution I have used it.

