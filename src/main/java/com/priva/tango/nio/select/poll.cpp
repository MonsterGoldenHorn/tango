struct pollfd{

　int fd； // 文件描述符

　short event；// 请求的事件

　short revent；// 返回的事件

}

pollfd[] pollfds;
for(int i=0;i<5;i++)
{
	pollfds[i] = accept(...)
	pollfds[i].event = POLLIN;
}

while(1)
{
poll(pollfds,5,timeout);
 for(int i=0;i<5;i++){
 	if(pollfds[i].revent&POLLIN){
 		pollfds[i].event=0;
 		read....
 	}
 }
}