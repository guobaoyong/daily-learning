clc;
clear;
% P83 FIGURE3.8及P84 FIGURE3.9
f=imread('Fig3.15(a)1top.jpg');
subplot(231),imshow(f);
title('原图');
subplot(234),imhist(f);
title('直方图')
ylim('auto')
g=histeq(f,256);
subplot(232),imshow(g)
title('直方图均衡化后的图像')
subplot(235),imhist(g)
title('均衡化后的直方图')
ylim('auto')
hnorm=imhist(f)./length(f(:));
cdf=cumsum(hnorm);
x=linspace(0,1,256);
subplot(233),plot(x,cdf)
axis([0 1 0 1])
set(gca,'xtick',0:.2:1)
set(gca,'ytick',0:.2:1)
xlabel('输入灰度级','FontSize',9)
xlabel('输出灰度级','FontSize',9)
% Specify text in the body of the graph:
text(0.18,0.5,'变换函数','FontSize',9)
