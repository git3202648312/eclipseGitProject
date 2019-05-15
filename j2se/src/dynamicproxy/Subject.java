package dynamicproxy;

//真实角色和代理角色共同维护的接口(不能是抽象类)
public interface Subject {
	int rent();//租房
}
