package by.segg3r.epam.presentation.springinternals.beans;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class ExtensionDeployer {

	@Autowired
	private List<Extension> extensions;
	
	public void deployExtensions() {
		extensions.forEach((extension) -> {
			System.out.println("Deploying " + extension.getClass());
		});
	}
	
}
