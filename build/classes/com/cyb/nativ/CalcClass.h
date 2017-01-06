

#ifndef _MY_POINT_H
#define _MY_POINT_H

#ifdef _MY_DLL_FILE
class _declspec(dllexport) MyCalc	// 导出
#else
class _declspec(dllimport) MyCalc	// 导入
#endif

{
public:
    MyCalc();
	int MyAdd(int x, int y);
	int MySub(int x, int y);
	int MyMul(int x, int y);
	int MyDiv(int x, int y);
};

#endif

