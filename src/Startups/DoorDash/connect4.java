package DoorDash;

/**
 * Created by cicean on 8/16/2018.
 */
public class connect4 {

    public static int dudgeWin(int[][] qipan,int y,int x) //�ж��Ƿ������������һ���� ���0�Ǻ���Ӯ�� -1�ǰ���Ӯ��
    {
        int c = 0; //����
        int qi = qipan[y][x];
        //����
        for(int i= 0 ;i<11;i++) //�����ڵ����б�������
        {
            if(qi == qipan[i][x])
            {
                c++;    //������ͬ�����Ӿͼ�һ
                if(c>=5)
                {
                    return qi;
                }
            }else
            {
                c = 0;    //�������һ����ͬ�� �Ǿ͹�һ
            }
        }

        c = 0; //���濪ʼ��������
        for(int i= 0 ;i<11;i++) //�����ڵ����б�������
        {
            if(qi == qipan[y][i])
            {
                c++;    //������ͬ�����Ӿͼ�һ
                if(c>=5)
                {
                    return qi;
                }
            }else
            {
                c = 0;    //�������һ����ͬ�� �Ǿ͹�һ
            }
        }

        c = 0;     // ƽ�������Խ���
        if(x>y) //���Խ�����
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
        }else if(x<y) //���Խ�����
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
        }else //�����Խ�����
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

        c = 0;     //ƽ���ڸ��Խ��ߵ�����
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
        return -2; //û��ʤ�� �Ǿ��Ƿ���-2
    }
}
