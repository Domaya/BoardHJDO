package kr.or.kosa.action;
/*
	Servlet(Front) 요청 받는다
		1. 화면 요청
		2. 로직 처리 요청
	
	화면 아니면 로직
*/
public class ActionForward {
	private boolean isRedirect = false; //뷰의 전환 여부 redirect or forward
	private String path = null;	//이동 경로
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}