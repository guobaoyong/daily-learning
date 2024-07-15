clc;
clear;
% 本程序绘出直方图均匀化的变换函数
% 以及变化前后的直方图
k=0:7;
rk=k/7;
nk=[790 1023 850 656 329 245 122 81];
n=sum(nk(:));
Pr=nk/n;
subplot(131)
stem(rk,Pr)
xlabel('rk')
ylabel('P_r(r_k)')
title('均匀化前的直方图')
Tr=cumsum(Pr,2); % 沿列的方向求累计和
subplot(132)
stem(rk,Tr)
xlabel('rk')
ylabel('s_k=T(r_k)')
title('变换函数')
ns=zeros(1,8);
for i=0:7
    idx=find(Tr>=(2*i-1)/14&Tr<(2*i+1)/14);
    m0=nk(idx);
    ns(i+1)=sum(m0(:));
end
sums=sum(ns(:));
Ps=ns/sums;
subplot(133)
stem(rk,Ps)
xlabel('s_k')
ylabel('P_s(s_k)')
title('均匀化后的直方图')

