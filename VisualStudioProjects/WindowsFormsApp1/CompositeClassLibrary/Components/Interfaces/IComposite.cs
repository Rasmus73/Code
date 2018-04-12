namespace IntervalDisplayLibrary.Components.Interface
{
    internal interface IComposite
    {
        long YCoord { get; set; }

        //void CalculatePosition(int width, int resolutionInPixels);

        void Clear();
    }
}