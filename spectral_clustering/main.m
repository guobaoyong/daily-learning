
%XOR数据集
load('XOR.mat');
dataSet=XOR;
dataSet=dataSet/max(max(abs(dataSet)));
num_clusters=4;
sigma=0.1;
Z=pdist(dataSet); % Matlab里的一个函数，常用在聚类算法中，用来获得每两个点之间的距离。
W=squareform(Z); % 将Z转换成N*N的矩阵
C = spectral(W,sigma, num_clusters);
plot(dataSet(C==1,1),dataSet(C==1,2),'r.', dataSet(C==2,1),dataSet(C==2,2),'b.', dataSet(C==3,1),dataSet(C==3,2),'g.', dataSet(C==4,1),dataSet(C==4,2),'m.');