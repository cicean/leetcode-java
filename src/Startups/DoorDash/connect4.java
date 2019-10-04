package DoorDash;

/**
 * Created by cicean on 8/16/2018.
 */
public class connect4 {

    public static int dudgeWin(int[][] qipan,int y,int x) //判断是否五个棋子连在一起了 如果0是黑棋赢了 -1是白棋赢了
    {
        int c = 0; //计数
        int qi = qipan[y][x];
        //上下
        for(int i= 0 ;i<11;i++) //在所在的列中暴力搜索
        {
            if(qi == qipan[i][x])
            {
                c++;    //遇到相同的棋子就加一
                if(c>=5)
                {
                    return qi;
                }
            }else
            {
                c = 0;    //如果出现一个不同的 那就归一
            }
        }

        c = 0; //下面开始左右搜索
        for(int i= 0 ;i<11;i++) //在所在的行中暴力搜索
        {
            if(qi == qipan[y][i])
            {
                c++;    //遇到相同的棋子就加一
                if(c>=5)
                {
                    return qi;
                }
            }else
            {
                c = 0;    //如果出现一个不同的 那就归一
            }
        }

        c = 0;     // 平行于主对角线
        if(x>y) //主对角线上
        {
            for(int i=0,j=x-y;i<11 && j<11;i++,j++)
            {
                if(qipan[i][j] == qi)
                {
                    c++;
                    if(c >= 5)
                    {
                        return qi;
                    }
                }else
                {
                    c = 0;
                }
            }
        }else if(x<y) //主对角线下
        {
            for(int i=y-x,j=0;i<11 && j<11;i++,j++)
            {
                if(qipan[i][j] == qi)
                {
                    c++;
                    if(c >= 5)
                    {
                        return qi;
                    }
                }else
                {
                    c = 0;
                }
            }
        }else //在主对角线上
        {
            for(int i=0,j=0;i<11 && j<11;i++,j++)
            {
                if(qipan[i][j] == qi)
                {
                    c++;
                    if(c >= 5)
                    {
                        return qi;
                    }
                }else
                {
                    c = 0;
                }
            }
        }

        c = 0;     //平行于副对角线的搜索
        for(int i=y,j=x;i>=0 && j<11;i--,j++)
        {
            if(qipan[i-1][j+1] == qi)
            {
                c++;
                if(c >= 5)
                {
                    return qi;
                }
            }else
            {
                break;
            }
        }

        for(int i=y,j=x;i<11 && j>=0;i++,j--)
        {
            if(qipan[i][j] == qi)
            {
                c++;
                if(c >= 5)
                {
                    return qi;
                }
            }else
            {
                break;
            }
        }
        return -2; //没有胜利 那就是返回-2
    }
}
