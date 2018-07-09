package minimizer;

public class Application
{
	private final int WIDTH = 640;
	private final int HEIGHT = 480;
	
	public Application()
	{
		ApplicationModel model = new ApplicationModel();
		ApplicationView view = new ApplicationView(WIDTH, HEIGHT);
		@SuppressWarnings("unused")	// Verändert model und view
		ApplicationController controller = new ApplicationController(model, view);
	}
}
