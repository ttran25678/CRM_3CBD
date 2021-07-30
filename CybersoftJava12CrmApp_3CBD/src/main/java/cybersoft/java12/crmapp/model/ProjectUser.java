package cybersoft.java12.crmapp.model;

public class ProjectUser {

	private User user;
	private Project project;
	private String join_date;
	private String join_description;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public String getJoin_date() {
		return join_date;
	}
	public void setJoin_date(String join_date) {
		this.join_date = join_date;
	}
	public String getJoin_description() {
		return join_description;
	}
	public void setJoin_description(String join_description) {
		this.join_description = join_description;
	}
	
	
}
