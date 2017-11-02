package readbankinfo.g100.com.rxjavademo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observables.GroupedObservable;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.btn_map)
    Button btnMap;
    @InjectView(R.id.btn_flatmap)
    Button btnFlatmap;
    @InjectView(R.id.btn_groupby)
    Button btnGroupby;
    @InjectView(R.id.btn_buffer)
    Button btnBuffer;
    @InjectView(R.id.btn_Debounce)
    Button btnDebounce;
    @InjectView(R.id.btn_Distinct)
    Button btnDistinct;
    @InjectView(R.id.btn_ElementAt)
    Button btnElementAt;
    @InjectView(R.id.btn_Filter)
    Button btnFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        //btnFlatmap.setOnClickListener(new);
    }

    @OnClick({R.id.btn_map, R.id.btn_flatmap, R.id.btn_buffer,R.id.btn_Debounce,R.id.btn_Distinct,R.id.btn_ElementAt,R.id.btn_Filter,R.id.btn_First
    ,R.id.btn_IgnoreElements,R.id.btn_Last,R.id.btn_Sample,R.id.btn_Skip,R.id.btn_Take,
    R.id.btn_Zip,R.id.btn_Merge,R.id.btn_StartWith,R.id.btn_CombineLatest,R.id.btn_Join,R.id.btn_SwitchOnNext})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_map:
                map();
                break;
            case R.id.btn_flatmap:
                flatMap();
                break;
            case R.id.btn_buffer:
                Log.i("wang", "=====232======");
                buffer();
                break;
            case R.id.btn_Debounce:
                debounce();
                break;
            case R.id.btn_Distinct:
                distinct();
                break;
            case R.id.btn_ElementAt:
                elementAt();
                break;
            case R.id.btn_Filter:
                filter();
                break;
            case R.id.btn_IgnoreElements:
                IgnoreElements();
                break;
            case R.id.btn_Last:
                Last();
                break;
            case R.id.btn_Sample:
                Sample();
                break;
            case R.id.btn_Skip:
                Skip();
                break;
            case R.id.btn_First:
                first();
                break;
            case R.id.btn_Take:
                Take();
                break;
            case R.id.btn_Zip:
                btn_Zip();
                break;
            case R.id.btn_Merge:
                btn_Merge();
                break;
            case R.id.btn_StartWith:
                btn_StartWith();
                break;
            case R.id.btn_CombineLatest:
                btn_CombineLatest();
                break;
            case R.id.btn_Join:
                btn_Join();
                break;
            case R.id.btn_SwitchOnNext:
                btn_SwitchOnNext();
                break;

        }
    }

    /**
     * 用来合并两个Observable发射的数据项，根据fun2函数生成一个新的值并发射出去。当其中一个Observable发送数据结束或异常时
     * 另一个Observable也将停止发送数据
     */
    private void btn_Zip() {
        Observable<Integer> boservable1 = Observable.just(1,2,3,4);
        Observable<Integer> boservable2 = Observable.just(10,20,30,40,11);
        Observable.zip(boservable1, boservable2, new Func2<Integer, Integer, String>() {
            @Override
            public String call(Integer integer, Integer integer2) {
                return integer+integer2+"!";
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.i("wang","onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i("wang","onError");
            }

            @Override
            public void onNext(String s) {
                Log.i("wang",s);
            }
        });

    }

    /**
     * 将两个Observable发射的事件序列组合并成一个事件序列(按照observable生成的时间先后来合并)。就想是一个observable发射的一样。
     * 你可以简单的理解为两个Observable合并成了一个Observable
     */
    private void btn_Merge() {
        Observable<Integer> odds = Observable.just(1,3,5);
        Observable<Integer> evens = Observable.just(2,4,6,8);
        Observable.merge(odds,evens).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.i("wang","onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i("wang","onError");
            }

            @Override
            public void onNext(Integer integer) {
                Log.i("wang",""+integer);
            }
        });
    }
    private void btn_StartWith() {

        Observable<Integer> odds = Observable.just(1,3,5);
        Observable<Integer> evens = Observable.just(2,4,6,8);
         evens.startWith(odds).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.i("wang","onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i("wang","onError");
            }

            @Override
            public void onNext(Integer integer) {
                Log.i("wang",""+integer);
            }
        });
    }

    /**
     * 用于将两个Observable最近发射的数据用fun2函数的规则进行组合
     */
    private void btn_CombineLatest() {
        Observable<Integer> odds = Observable.just(1,3,5);
        Observable<Integer> evens = Observable.just(2,4,6,8);
       odds.combineLatest(odds, evens, new Func2<Integer, Integer, Integer>() {
           @Override
           public Integer call(Integer integer, Integer integer2) {
               Log.i("wang","Integer1:"+ integer+" integer2:"+integer2);
               return integer+integer2;
           }
       }).subscribe(new Subscriber<Integer>() {
           @Override
           public void onCompleted() {

           }

           @Override
           public void onError(Throwable e) {

           }

           @Override
           public void onNext(Integer integer) {
               Log.i("wang",integer+"");

           }
       });
    }
    private void btn_Join() {
    }
    private void btn_SwitchOnNext() {
    }

    private void Take() {
        Observable.just(1,2,3,4).take(2).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.i("wang","onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i("wang","onError");
            }

            @Override
            public void onNext(Integer integer) {
                Log.i("wang","onNext:"+integer);
            }
        });
    }
    private void Skip() {
        Observable.just(1,2,3,4).skip(2).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.i("wang","onNext:"+integer);
            }
        });
    }
    private void Sample() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                try{
                    for(int i=0;i<10;i++){
                        Thread.sleep(600);
                        subscriber.onNext(i);
                    }

                }catch (InterruptedException ee){
                    subscriber.onError(ee);
                }
            }
        }).sample(2,TimeUnit.SECONDS).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.i("wang","onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i("wang","onError");
            }

            @Override
            public void onNext(Integer integer) {
                Log.i("wang","onNext:"+integer);

            }
        });
    }
    private void Last() {
    }

    private void IgnoreElements() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onNext(23);
                throw new NullPointerException();
            }
        }).ignoreElements().subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.i("wang","onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i("wang","onError:"+e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                Log.i("wang","onNext:"+integer);

            }
        });


        Observable.just(123).ignoreElements().subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.i("wang","onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i("wang","onError:"+e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                Log.i("wang","onNext:"+integer);

            }
        });
    }

    private void first() {
        Observable.just(1,2,3,4,5,4,3).distinct().first().subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.i("wang",""+integer);

            }
        });
    }

    private void filter() {
        Observable.just(1,2,3,4,5,4,3).distinct().filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer>3;
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.i("wang",""+integer);

            }
        });
    }

    private void elementAt() {
        Observable.just(1,2,3,4,5,4,3).elementAt(4).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.i("wang",""+integer);

            }
        });
    }

    private void distinct() {
        Observable.just(1,2,3,4,5,4,3).distinct().subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.i("wang",""+integer);

            }
        });
    }

    private void debounce() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> arg0) {
                try{
                    for(int i=0; i<10;i++){
                        Thread.sleep(1000);
                        arg0.onNext(i);
                    }
                    arg0.onCompleted();
                }catch (Exception e){
                    arg0.onError(e);
                }
            }
        }).debounce(1, TimeUnit.SECONDS).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.i("wang","onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i("wang","onError:"+e.getMessage());

            }

            @Override
            public void onNext(Integer integer) {
                Log.i("wang",integer+"");

            }
        });
    }

    private void buffer() {
        /*Observable.range(1,5).buffer(2)
                .subscribe(new Observer<List<Integer>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Integer> integers) {
                        //System.out.println(integers);
                        for(int i=0;i<integers.size();i++){
                            Log.i("wang",integers.get(i)+"");
                        }
                        Log.i("wang","===========");

                    }
                });*/
        Observable.range(1, 6).buffer(2)
                .subscribe(new Subscriber<List<Integer>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Integer> integers) {
                        //System.out.println(integers);
                        for (int i = 0; i < integers.size(); i++) {
                            Log.i("wang", integers.get(i) + "");
                        }
                        Log.i("wang", "===========");

                    }
                });
    }

    private void map() {
        Observable.just(1, 2, 3).map(new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                return integer + 100 + "";
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.d("wang", s);

            }
        });
    }

    /**
     * 解决多层嵌套的问题。
     * 例如网络请求时，第二个请求时基于第一个网络请求的结果进行操作。
     */

    private void flatMap() {


        Observable.just(1, 2, 3, 4, 5, 6).flatMap(new Func1<Integer, Observable<? extends String>>() {
            @Override
            public Observable<? extends String> call(Integer integer) {
                return Observable.just(integer + 10 + "");
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.i("wang", s);
            }
        });
    }

    @OnClick(R.id.btn_groupby)
    public void onClick() {
        Observable.just(1, 2, 3, 4, 5, 6).groupBy(new Func1<Integer, Integer>() {
            @Override
            public Integer call(Integer integer) {
                return integer % 2;
            }
        }).subscribe(new Observer<GroupedObservable<Integer, Integer>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(final GroupedObservable<Integer, Integer> integerIntegerGroupedObservable) {
                integerIntegerGroupedObservable.subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.i("wang", "group:" + integerIntegerGroupedObservable.getKey() + "  data:" + integer);
                    }
                });
            }
        });
    }

}
